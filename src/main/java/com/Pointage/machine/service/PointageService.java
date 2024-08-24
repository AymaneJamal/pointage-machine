package com.Pointage.machine.service;

import com.Pointage.machine.dto.PointageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class PointageService {

    private final RestTemplate restTemplate;
    private final AuthService authService;

    public String envoyerPointage(PointageRequest pointageRequest) {
        String token = authService.authenticate();

        // Envoyer le pointage à l'API principale
        String url = "http://localhost:8080/api/v1/machine/pointage";  // URL de l'API principale
        var headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        var request = new HttpEntity<>(pointageRequest, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

        return response.getBody();
    }
}
