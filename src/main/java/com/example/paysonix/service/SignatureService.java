package com.example.paysonix.service;


import java.util.Map;

public interface SignatureService {
    Map<String, Object> generateSignature(String operationId, Map<String, String> params);
}
