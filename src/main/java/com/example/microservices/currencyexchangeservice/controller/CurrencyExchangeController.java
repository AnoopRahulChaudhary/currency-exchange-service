package com.example.microservices.currencyexchangeservice.controller;

import com.example.microservices.currencyexchangeservice.dto.CurrencyExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {

    private final Environment environment;

    @Autowired
    public CurrencyExchangeController(Environment environment) {
        this.environment = environment;
    }

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange getExchangeValue(@PathVariable String from, @PathVariable String to){
        String port = environment.getProperty("local.server.port");
        return new CurrencyExchange(1000l, "USD", "INR", 65.0f, port);
    }
}
