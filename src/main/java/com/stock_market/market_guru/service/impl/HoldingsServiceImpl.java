package com.stock_market.market_guru.service.impl;

import com.stock_market.market_guru.entity.Holdings;
import com.stock_market.market_guru.repository.HoldingsRepository;
import com.stock_market.market_guru.service.HoldingsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HoldingsServiceImpl implements HoldingsService {

    private static final Logger logger = LoggerFactory.getLogger(HoldingsServiceImpl.class);

    @Autowired
    private HoldingsRepository holdingsRepository;

    @Override
    @Cacheable(value = "holdings-1", key = "#userId")
    public List<Holdings> getHoldingsByUser(String userId) {
        // logger.info("Fetching all holdings for user: {}", userId);
        return holdingsRepository.findAll();
    }

    @Override
    @Cacheable(value = "holdings-2", key = "#userId")
    public List<Holdings> getAllHoldings(Integer userId) {
        // logger.info("Fetching all holdings");
        return holdingsRepository.findAllByUserId(userId);
    }
} 