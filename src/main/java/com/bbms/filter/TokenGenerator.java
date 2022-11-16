package com.bbms.filter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.bbms.config.SecurityContants;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class TokenGenerator extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth != null) {
			SecretKey key = Keys.hmacShaKeyFor(SecurityContants.JWT_KEY.getBytes(StandardCharsets.UTF_8));
			String jwt = Jwts.builder().setIssuer("ZHASK").setSubject("jwt")
						.claim("user",auth.getName())
						.setIssuedAt(new Date())
						.setExpiration(new Date((new Date()).getTime()+(48*3600000)))
						.signWith(key).compact();
			System.out.println("toekn generated.");
					response.setHeader(SecurityContants.JWT_HEADER,jwt);
		}
		filterChain.doFilter(request, response);
	}
	
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) {
		if(request.getServletPath().equals("/")) {
			return false;
		}
		return true;
	}

}
