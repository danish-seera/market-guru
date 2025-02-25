package com.stock_market.market_guru.repository;

import com.stock_market.market_guru.entity.StockRawSnapshot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRawSnapshotRepository extends JpaRepository<StockRawSnapshot, Integer> {
    // Custom query methods can be added here
    // Example:
     List<StockRawSnapshot> findByExchangeAndInstrument(String exchange, String instrument);
}
