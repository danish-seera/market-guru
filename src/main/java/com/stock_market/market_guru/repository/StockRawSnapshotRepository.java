package com.stock_market.market_guru.repository;

import com.stock_market.market_guru.entity.StockRawSnapshot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StockRawSnapshotRepository extends JpaRepository<StockRawSnapshot, Integer> {
     List<StockRawSnapshot> findByExchangeAndInstrument(String exchange, String instrument);

     Optional<StockRawSnapshot> findByExchangeAndInstrumentAndSourceAndCategory(String exchange, String instrument, String source, String category);
}
