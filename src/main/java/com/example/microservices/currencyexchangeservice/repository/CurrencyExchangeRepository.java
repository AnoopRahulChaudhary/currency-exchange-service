package com.example.microservices.currencyexchangeservice.repository;

import com.example.microservices.currencyexchangeservice.model.CurrencyExchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, Integer> {

    CurrencyExchange findByFromAndTo(String from, String to);
}
