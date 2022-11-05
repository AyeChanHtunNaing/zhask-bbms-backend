package com.bbms.provider;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.bbms.dto.UserDto;
import com.bbms.repository.UserRepository;



@Component
public class LoginAuthProvider implements AuthenticationProvider{

	@Autowired
	private UserRepository repo;
	
	@Autowired
	private PasswordEncoder encdr;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String pwd = authentication.getCredentials().toString();
		UserDto usr = repo.findByEmail(username);
		if (usr != null) {
			System.out.println(usr.isStatus());
			if (encdr.matches(pwd, usr.getPassword())&&usr.isStatus()) {
				List<GrantedAuthority> authorities = new ArrayList<>();
				System.out.println("authentication success");
				return new UsernamePasswordAuthenticationToken(username, pwd, authorities);
			} else {
				throw new BadCredentialsException("Invalid password!");
			}
		}else {
			throw new BadCredentialsException("No user registered with this details!");
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
	
}
