package com.stock_market.market_guru.service;

import com.stock_market.market_guru.entity.TradeBook;
import java.util.List;
import java.util.Optional;

public interface TradeBookService {
    List<TradeBook> getAllTrades(Integer userId);
    Optional<TradeBook> getTradeById(Long id);
    List<TradeBook> getTradesByBroker(String broker);
    List<TradeBook> getTradesByStock(String instrument);
} 