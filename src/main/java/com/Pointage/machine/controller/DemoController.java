package com.Pointage.machine.controller;

import com.Pointage.machine.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/v1/demo")
@RequiredArgsConstructor
public class DemoController {

    private final AuthService authService;
    private final RestTemplate restTemplate;

    @GetMapping
    public ResponseEntity<String> callDemoEndpoint() {
        // Authentification pour obtenir le token JWT
        String token = authService.getToken();

        // Préparation de l'en-tête avec le token JWT
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);

        // Préparation de la requête
        HttpEntity<String> request = new HttpEntity<>(headers);

        // Envoi de la requête à l'application principale
        String url = "http://localhost:8080/api/v1/demo"; // L'URL du endpoint de l'application principale
        ResponseEntity<String> response = restTemplate.exchange(url, org.springframework.http.HttpMethod.GET, request, String.class);

        // Retourne la réponse de l'application principale
        return ResponseEntity.ok(response.getBody());
    }
}
