package com.Pointage.machine.service;


import com.Pointage.machine.dto.AuthRequest;
import com.Pointage.machine.model.AuthResponse;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
@RequiredArgsConstructor
public class AuthService {

    private final RestTemplate restTemplate;

    @Value("${auth.url}")
    private String authUrl;

    @Value("${machine.email}")
    private String email;

    @Value("${machine.password}")
    private String password;

    private String jwtToken;

    @PostConstruct
    public void authenticateAtStartup() {
        this.jwtToken = authenticate();
    }

    public String authenticate() {
        AuthRequest authRequest = new AuthRequest();
        authRequest.setEmail(email);
        authRequest.setPassword(password);

        AuthResponse authResponse = restTemplate.postForObject(authUrl, authRequest, AuthResponse.class);
        return authResponse.getToken();
    }

    public String getToken() {
        if (jwtToken == null) {
            jwtToken = authenticate();
        }
        return jwtToken;
    }
}