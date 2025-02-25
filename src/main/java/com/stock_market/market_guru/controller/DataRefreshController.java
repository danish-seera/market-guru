package com.stock_market.market_guru.controller;

import com.stock_market.market_guru.service.GuruFocusService;
import com.stock_market.market_guru.service.WatchlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/watchlist")
public class DataRefreshController {

    @Autowired
    private WatchlistService watchlistService;
    @Autowired
    private GuruFocusService guruFocusService;

    @GetMapping("/refresh-guru-focus")
    public String refreshGuruFocus() {
        guruFocusService.refreshSnapshot();
        return "Success";
    }
}
