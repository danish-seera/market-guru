package com.stock_market.market_guru.service.impl;

import com.stock_market.market_guru.entity.TradeBook;
import com.stock_market.market_guru.repository.TradeBookRepository;
import com.stock_market.market_guru.service.TradeBookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TradeBookServiceImpl implements TradeBookService {

    private static final Logger logger = LoggerFactory.getLogger(TradeBookServiceImpl.class);

    @Autowired
    private TradeBookRepository tradeBookRepository;

    @Override
    public List<TradeBook> getAllTrades(Integer userId) {
        // logger.info("Fetching all trades");
        return tradeBookRepository.findAllByUserId(userId);
    }

    @Override
    public Optional<TradeBook> getTradeById(Long id) {
        // logger.info("Fetching trade with id: {}", id);
        return tradeBookRepository.findById(id);
    }

    @Override
    public List<TradeBook> getTradesByBroker(String broker) {
        // logger.info("Fetching all trades for broker: {}", broker);
        return tradeBookRepository.findByBroker(broker);
    }

    @Override
    public List<TradeBook> getTradesByStock(String instrument) {
        // logger.info("Fetching all trades for stock: {}", instrument);
        return tradeBookRepository.findByInstrument(instrument);
    }
} 