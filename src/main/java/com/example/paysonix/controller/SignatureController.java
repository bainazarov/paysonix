package com.example.paysonix.controller;

import com.example.paysonix.service.SignatureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/signature")
@RequiredArgsConstructor
public class SignatureController {

    private final SignatureService signatureService;

    @PostMapping("/{operationId}")
    public ResponseEntity<Map<String, Object>> generateSignature(
            @PathVariable String operationId,
            @RequestBody Map<String, String> params) {

        Map<String, Object> response = signatureService.generateSignature(operationId, params);

        return ResponseEntity.ok(response);
    }
}
