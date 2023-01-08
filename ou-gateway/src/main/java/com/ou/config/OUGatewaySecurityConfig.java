package com.ou.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration(proxyBeanMethods = false)
@EnableWebFluxSecurity
public class OUGatewaySecurityConfig {

	@Bean
	public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
		http.csrf().disable().authorizeExchange()
				.pathMatchers(HttpMethod.POST, "/oua/api/v1/company", "/oua/api/v1/auth/**").permitAll().anyExchange()
				.authenticated().and().oauth2ResourceServer().jwt();
		return http.build();
	}
}
