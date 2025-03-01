package com.stock_market.market_guru.repository;

import com.stock_market.market_guru.entity.TradeBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TradeBookRepository extends JpaRepository<TradeBook, Long> {
    List<TradeBook> findByBroker(String broker);
    List<TradeBook> findByExchangeAndInstrument(String exchange, String instrument);
    List<TradeBook> findByInstrument(String instrument);
    List<TradeBook> findAllByUserId(Integer userId);
} 