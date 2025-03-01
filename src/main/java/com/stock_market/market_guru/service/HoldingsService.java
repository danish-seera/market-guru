package com.stock_market.market_guru.service;

import com.stock_market.market_guru.entity.Holdings;
import java.util.List;

public interface HoldingsService {
    List<Holdings> getHoldingsByUser(String userId);
    List<Holdings> getAllHoldings(Integer userId);
} 