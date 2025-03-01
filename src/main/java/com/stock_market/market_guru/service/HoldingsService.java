package com.stock_market.market_guru.service;

import com.stock_market.market_guru.entity.Holdings;
import java.util.List;
import java.util.Optional;

public interface HoldingsService {
    List<Holdings> getHoldingsByUser(String userId);
    List<Holdings> getAllHoldings();
} 