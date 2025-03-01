package com.stock_market.market_guru.controller;

import com.stock_market.market_guru.entity.TradeBook;
import com.stock_market.market_guru.mapper.TradeBookMapper;
import com.stock_market.market_guru.response.TradeBookResponse;
import com.stock_market.market_guru.service.TradeBookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;  
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/trades")
public class TradeBookController {
    
    private static final Logger logger = LoggerFactory.getLogger(TradeBookController.class);
    
    @Autowired
    private TradeBookService tradeBookService;
    
    @Autowired
    private TradeBookMapper tradeBookMapper;

    // Get all trades
    @GetMapping
    public ResponseEntity<List<TradeBookResponse>> getAllTrades(@RequestParam(name = "userId", required = false) Integer userId) {
        logger.info("Received request to fetch all trades for user: {}", userId);
        try {
            List<TradeBook> trades = tradeBookService.getAllTrades(userId);
            List<TradeBookResponse> responses = trades.stream()
                    .map(tradeBookMapper::buildTradeResponse)
                    .collect(Collectors.toList());
            
            // logger.info("Successfully fetched {} trades", trades.size());
            return ResponseEntity.ok(responses);
        } catch (Exception e) {
            logger.error("Error fetching all trades: {}", e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    // Get trade by ID
    // @GetMapping("/{id}")
    // public ResponseEntity<TradeBookResponse> getTradeById(@PathVariable Long id) {
    //     logger.info("Received request to fetch trade with id: {}", id);
    //     try {
    //         return tradeBookService.getTradeById(id)
    //                 .map(trade -> {
    //                     logger.info("Successfully fetched trade with id: {}", trade.getTradeId());
    //                     return ResponseEntity.ok(tradeBookMapper.buildTradeResponse(trade));
    //                 })
    //                 .orElseGet(() -> {
    //                     logger.warn("Trade not found with id: {}", id);
    //                     return ResponseEntity.notFound().build();
    //                 });
    //     } catch (Exception e) {
    //         logger.error("Error fetching trade with id {}: {}", id, e.getMessage());
    //         return ResponseEntity.internalServerError().build();
    //     }
    // }

    // Get trades by broker
    @GetMapping("/broker/{broker}")
    public ResponseEntity<List<TradeBookResponse>> getTradesByBroker(@PathVariable String broker) {
        logger.info("Received request to fetch trades for broker: {}", broker);
        try {
            List<TradeBook> trades = tradeBookService.getTradesByBroker(broker);
            List<TradeBookResponse> responses = trades.stream()
                    .map(tradeBookMapper::buildTradeResponse)
                    .collect(Collectors.toList());
            
            // logger.info("Successfully fetched {} trades for broker: {}", trades.size(), broker);
            return ResponseEntity.ok(responses);
        } catch (Exception e) {
            logger.error("Error fetching trades for broker {}: {}", broker, e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    // Get trades by stock
    @GetMapping("/{instrument}")
    public ResponseEntity<List<TradeBookResponse>> getTradesByStock(@PathVariable String instrument) {
        logger.info("Received request to fetch trades for stock: {}", instrument);
        try {
            List<TradeBook> trades = tradeBookService.getTradesByStock(instrument);
            List<TradeBookResponse> responses = trades.stream()
                    .map(tradeBookMapper::buildTradeResponse)
                    .collect(Collectors.toList());
            
            // logger.info("Successfully fetched {} trades for stock: {}", trades.size(), instrument);
            return ResponseEntity.ok(responses);
        } catch (Exception e) {
            logger.error("Error fetching trades for stock {}: {}", instrument, e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
} 