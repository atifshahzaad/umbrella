package com.ou.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

import com.ou.dto.LoginDTO;
import com.ou.dto.LoginErrorDTO;
import com.ou.dto.LoginResponse;
import com.ou.exceptions.InvalidCredentialsException;
import com.ou.service.AuthService;

import reactor.core.publisher.Mono;

@Service
public class AuthServiceImpl implements AuthService {

    private static final String GRANT_TYPE = "password";

    @Value("${spring.security.oauth2.client.registration.keycloack.accessTokenUri}")
    private String keyCloackTokenUrl;

    @Value("${spring.security.oauth2.client.registration.keycloack.client-id}")
    private String keyCloackClient;

    @Value("${spring.security.oauth2.client.registration.keycloack.client-secret}")
    private String keyCloackSecret;

    private final WebClient webClient;

    public AuthServiceImpl() {
        this.webClient = WebClient.builder()
                .filter(ExchangeFilterFunction.ofResponseProcessor(this::renderApiErrorResponse)).build();
    }

    @Override
    public LoginResponse login(LoginDTO dto) {
        Mono<LoginResponse> response = webClient.post()
                .uri(keyCloackTokenUrl)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData(getFormData(dto)))
                .retrieve()
                .bodyToMono(LoginResponse.class);

        return response.block();
    }

    private Mono<ClientResponse> renderApiErrorResponse(ClientResponse clientResponse) {
        if (clientResponse.statusCode().isError()) {
            return clientResponse.bodyToMono(LoginErrorDTO.class)
                    .flatMap(apiErrorResponse -> Mono.error(new InvalidCredentialsException(apiErrorResponse)));
        }
        return Mono.just(clientResponse);
    }

    private MultiValueMap<String, String> getFormData(LoginDTO dto) {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();

        formData.add("client_id", keyCloackClient);
        formData.add("client_secret", keyCloackSecret);
        formData.add("username", dto.getUsername());
        formData.add("password", dto.getPassword());
        formData.add("grant_type", GRANT_TYPE);

        return formData;
    }

}
