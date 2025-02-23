package com.stock_market.market_guru.controller;

import com.stock_market.market_guru.service.WatchlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/watchlist")
public class WatchlistController {

    @Autowired
    private WatchlistService watchlistService;

    @GetMapping("/refresh")
    public String refreshWatchlist() {
        watchlistService.refreshWatchlist();
        return "Success";
    }
}
