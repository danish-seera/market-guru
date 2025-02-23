package com.stock_market.market_guru.service;

import com.stock_market.market_guru.client.GuruFocusClient;

import com.stock_market.market_guru.client.respone.GuruFocusSummaryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GuruFocusService {

    @Autowired
    private GuruFocusClient guruFocusClient;

    // Inject API key from configuration
    @Value("${gurufocus.api.key}")
    private String apiKey;

    public GuruFocusSummaryResponse getStockSummary(String symbol) {
        return guruFocusClient.getStockSummary(apiKey, symbol);
    }
}
