package com.riderguru.rider_guru.payment.internal;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Base64;
import java.util.Map;

@Slf4j
@Component
class RazorpayAdapter {

    private static final String RAZORPAY_API_URL = "https://api.razorpay.com/v1/";

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    RazorpayAdapter(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public Map<String, Object> createPaymentLink(Map<String, Object> paymentLinkRequest) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Basic " + getBasicAuth());

            HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(paymentLinkRequest, headers);

            String url = RAZORPAY_API_URL + "payment_links";
            ResponseEntity<String> response = restTemplate.exchange(
                    URI.create(url),
                    HttpMethod.POST,
                    requestEntity,
                    String.class
            );

            return objectMapper.readValue(response.getBody(), new TypeReference<>() {});
        } catch (Exception e) {
            log.error("Failed to create payment link : {}", e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    private String getBasicAuth() {
        String apiKey = "rzp_test_mw2iyETt0Pr6Lr";
        String apiSecret = "9ByQyTfoPzMeB2YADOcNiTTy";
        String auth = apiKey + ":" + apiSecret;
        return Base64.getEncoder().encodeToString(auth.getBytes());
    }
}
