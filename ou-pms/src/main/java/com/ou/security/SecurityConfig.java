package com.ou.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http.cors().and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
				.securityMatcher("*/**")
				.authorizeHttpRequests(
						autorizeRequests -> 
								autorizeRequests
								.requestMatchers(HttpMethod.POST, "*/**").permitAll()
								.requestMatchers(HttpMethod.OPTIONS).permitAll()
								.anyRequest().authenticated())
				.oauth2ResourceServer(oauth2 -> oauth2.jwt());
		http.csrf().disable();
		return http.build();
	}
}
