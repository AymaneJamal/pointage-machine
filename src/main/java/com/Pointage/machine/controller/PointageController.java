package com.Pointage.machine.controller;

import com.Pointage.machine.dto.PointageRequest;
import com.Pointage.machine.service.PointageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/pointage")
@RequiredArgsConstructor
public class PointageController {

    private final PointageService pointageService;

    @PostMapping
    public ResponseEntity<String> envoyerPointage(@RequestBody PointageRequest pointageRequest) {
        String response = pointageService.envoyerPointage(pointageRequest);
        return ResponseEntity.ok(response);
    }
}
