package com.stock_market.market_guru.service.impl;

import com.stock_market.market_guru.entity.Holdings;
import com.stock_market.market_guru.repository.HoldingsRepository;
import com.stock_market.market_guru.service.HoldingsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HoldingsServiceImpl implements HoldingsService {

    private static final Logger logger = LoggerFactory.getLogger(HoldingsServiceImpl.class);

    @Autowired
    private HoldingsRepository holdingsRepository;

    @Override
    public List<Holdings> getHoldingsByUser(String userId) {
        logger.info("Fetching all holdings for user: {}", userId);
        return holdingsRepository.findAll();
    }

    @Override
    public List<Holdings> getAllHoldings() {
        logger.info("Fetching all holdings");
        return holdingsRepository.findAll();
    }
} 