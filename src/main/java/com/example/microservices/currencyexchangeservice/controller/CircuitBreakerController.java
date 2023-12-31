package com.example.microservices.currencyexchangeservice.controller;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class CircuitBreakerController {

    @GetMapping("/sample-api")
    @Retry(name = "sample-api", fallbackMethod = "sampleApiFallback")
//    @CircuitBreaker(name = "default", fallbackMethod = "sampleApiFallback")
//    @RateLimiter(name = "default")
//    @Bulkhead(name = "default")
    public String sampleApi() {
        log.info("Sample api call received.");
        ResponseEntity<String> response = new RestTemplate()
                .getForEntity("http://localhost:8080/dummy-url", String.class);
        return response.getBody();
    }

    public String sampleApiFallback(Exception e){
        return "fallbackResponse";
    }
}
