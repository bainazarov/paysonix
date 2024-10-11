package com.example.paysonix.service;

import com.example.paysonix.config.HMACSHA256Generator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SignatureServiceImpl implements SignatureService {

    private final HMACSHA256Generator hmacsha256Generator;

    @Override
    public Map<String, Object> generateSignature(String operationId, Map<String, String> params) {

        String sortedParams = params.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining("&"));

        String signatureValue = hmacsha256Generator.generateHMACSHA256(sortedParams);

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("result", Collections.singletonList(Collections.singletonMap("signature", signatureValue)));

        return response;
    }
}
