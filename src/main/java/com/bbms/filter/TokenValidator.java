package com.bbms.filter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.bbms.config.SecurityContants;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class TokenValidator extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String jwt = request.getHeader(SecurityContants.JWT_HEADER);
		System.out.println();
		System.out.println("Token:" + jwt+".");
		if(jwt != null) {
			try {
				SecretKey key = Keys.hmacShaKeyFor(SecurityContants.JWT_KEY.getBytes(StandardCharsets.UTF_8));
				System.out.println();
				System.out.println("Key:"+key);
				Claims claims = Jwts.parserBuilder()
								.setSigningKey(key)
								.build()
								.parseClaimsJws(jwt)
								.getBody();
				String name = String.valueOf(claims.get("user"));
				System.out.println(name);
				Authentication auth = new UsernamePasswordAuthenticationToken(name,null,
						null);
				SecurityContextHolder.getContext().setAuthentication(auth);
				response.setHeader(SecurityContants.JWT_HEADER,jwt);
			}catch (Exception e) {
				throw new BadCredentialsException("Invalid Token received!");
			}
		}
		filterChain.doFilter(request, response);
	}

	 @Override
	 protected boolean shouldNotFilter(HttpServletRequest request) {
		 if(request.getServletPath().equals("/signup")) {
				return true;
		}
		 if(request.getServletPath().equals("/forgot_psw")) {
				return true;
		}
		 if(request.getServletPath().equals("/verify/**")) {
				return true;
		}
		 if(request.getServletPath().equals("/")) {
				return true;
		}if(request.getServletPath().equals("/reset_psw/**")) {
			return true;
		}
		 return false;
	 }
}
