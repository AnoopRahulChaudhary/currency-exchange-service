package com.example.microservices.currencyexchangeservice.controller;

import com.example.microservices.currencyexchangeservice.model.CurrencyExchange;
import com.example.microservices.currencyexchangeservice.repository.CurrencyExchangeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@Slf4j
public class CurrencyExchangeController {

    private final Environment environment;
    private final CurrencyExchangeRepository repository;

    @Autowired
    public CurrencyExchangeController(Environment environment, CurrencyExchangeRepository repository) {
        this.environment = environment;
        this.repository = repository;
    }

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange getExchangeValue(@PathVariable String from, @PathVariable String to){
        log.info("getExchangeValue called with - from {}, to {}", from, to);
        CurrencyExchange currencyExchange = repository.findByFromAndTo(from, to);

        if (Objects.isNull(currencyExchange)) {
            throw new RuntimeException(String.format("Unable to find data for %s and %s", from, to));
        }

        currencyExchange.setEnvironment(getEnvironmentDetails());
        return currencyExchange;
    }

    private String getEnvironmentDetails() {
        String port = environment.getProperty("local.server.port");
        String host = environment.getProperty("HOSTNAME");
        String version = "v11";

        return port + " " + version + " " + host;
    }
}
