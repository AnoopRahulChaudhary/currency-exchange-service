package com.example.microservices.currencyexchangeservice.controller;

import com.example.microservices.currencyexchangeservice.dto.CurrencyExchange;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange getExchangeValue(@PathVariable String from, @PathVariable String to){
        return new CurrencyExchange(1000l, "USD", "INR", 65.0f);
    }
}
