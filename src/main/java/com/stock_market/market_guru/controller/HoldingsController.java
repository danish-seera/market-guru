package com.stock_market.market_guru.controller;

import com.stock_market.market_guru.entity.Holdings;
import com.stock_market.market_guru.mapper.HoldingsMapper;
import com.stock_market.market_guru.response.HoldingsResponse;
import com.stock_market.market_guru.service.HoldingsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/holdings")
public class HoldingsController {
    
    private static final Logger logger = LoggerFactory.getLogger(HoldingsController.class);
    
    @Autowired
    private HoldingsService holdingsService;
    
    @Autowired
    private HoldingsMapper holdingsMapper;


    // Get all holdings
    @GetMapping
    public ResponseEntity<List<HoldingsResponse>> getAllHoldings() {
        logger.info("Received request to fetch all holdings");
        try {
            List<Holdings> holdings = holdingsService.getAllHoldings();
            List<HoldingsResponse> responses = holdings.stream()
                    .map(holdingsMapper::buildHoldingsResponse)
                    .collect(Collectors.toList());
            
            logger.info("Successfully fetched {} holdings", holdings.size());
            return ResponseEntity.ok(responses);
        } catch (Exception e) {
            logger.error("Error fetching all holdings: {}", e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    // Get all holdings for a user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<HoldingsResponse>> getHoldingsByUser(@PathVariable String userId) {
        logger.info("Received request to fetch all holdings for user: {}", userId);
        try {
            List<Holdings> holdings = holdingsService.getHoldingsByUser(userId);
            List<HoldingsResponse> responses = holdings.stream()
                    .map(holdingsMapper::buildHoldingsResponse)
                    .collect(Collectors.toList());
            
            logger.info("Successfully fetched {} holdings for user: {}", holdings.size(), userId);
            return ResponseEntity.ok(responses);
        } catch (Exception e) {
            logger.error("Error fetching holdings for user {}: {}", userId, e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

} 