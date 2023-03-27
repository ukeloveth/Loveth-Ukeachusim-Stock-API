package com.assessment.stockapi.repositories;

import com.assessment.stockapi.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StocksRepository extends JpaRepository<Stock, Long> {
    Optional<Stock> findByTicker(String ticker);
}
