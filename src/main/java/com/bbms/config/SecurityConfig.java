
package com.bbms.config;

import java.util.Arrays;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.bbms.filter.TokenGenerator;
import com.bbms.filter.TokenValidator;

@SuppressWarnings("deprecation")
//@EnableWebSecurity(debug = true)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().cors()
				.configurationSource(new CorsConfigurationSource() {

					@Override
					public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
						// TODO Auto-generated method stub
						CorsConfiguration config = new CorsConfiguration();
						config.setAllowedOrigins(Collections.singletonList(SecurityContants.FRONTEND_BASE_URL));
//				config.setAllowedOriginPatterns(Collections.singletonList("*"));
						config.setAllowedMethods(Collections.singletonList("*"));
						config.setAllowCredentials(true);
						config.setAllowedHeaders(Collections.singletonList("*"));
						config.setExposedHeaders(Arrays.asList("Authorization"));
						config.setMaxAge(5L);
						return config;
					}
				}).and().csrf().disable().addFilterBefore(new TokenValidator(), BasicAuthenticationFilter.class)
				.addFilterAfter(new TokenGenerator(), BasicAuthenticationFilter.class).authorizeRequests()
				.antMatchers("/signup").permitAll().antMatchers("/chatty/**").permitAll().antMatchers("/forgot_psw")
				.permitAll().antMatchers("/verify/**").permitAll().antMatchers("/reset_psw/**").permitAll()
				.antMatchers("/api/v1/attachment/**").permitAll().antMatchers("/api/v1/workspacejoin/**").permitAll()
				.antMatchers("/api/v1/boardjoin/**").permitAll().antMatchers("/api/v1/favorite/**").permitAll()
				.antMatchers("/api/v1/taskjoin/**").permitAll().antMatchers("/api/v1/report/**").permitAll().antMatchers("/api/v1/workspace/**").permitAll()
				.anyRequest().authenticated().and().httpBasic()
//		.and()  
//        .logout()  
//        .logoutUrl("/j_spring_security_logout")  
//        .logoutSuccessUrl("/")  
		;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
