package com.stock_market.market_guru.controller;

import com.stock_market.market_guru.entity.Watchlist;
import com.stock_market.market_guru.mapper.StockMapper;
import com.stock_market.market_guru.response.StockResponse;
import com.stock_market.market_guru.service.WatchlistService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/watchlist")
public class WatchlistController {
    
    private static final Logger logger = LoggerFactory.getLogger(WatchlistController.class);
    @Autowired
    private StockMapper stockMapper;
    @Autowired
    private WatchlistService watchlistService;

  // Get watchlist by exchange and instrument
  @GetMapping("/{exchange}/{instrument}")
  public ResponseEntity<StockResponse> getWatchlist(@PathVariable String exchange,
                                                @PathVariable String instrument) {
        logger.info("Received request to fetch watchlist with exchange: {}, instrument: {}", exchange, instrument);
        try {
            return watchlistService.getWatchlistByExchangeAndInstrument(exchange, instrument)
                    .map(watchlist -> {
                        // logger.info("Successfully fetched watchlist: {}", watchlist.getCompanyName());
                        return ResponseEntity.ok(stockMapper.buildStockResponse(watchlist));
                    })
                    .orElseGet(() -> {
                        logger.warn("Watchlist not found with exchange: {}, instrument: {}", exchange, instrument);
                        return ResponseEntity.notFound().build();
                    });
        } catch (Exception e) {
            logger.error("Error fetching watchlist: {}", e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    // Get all watchlists
    @GetMapping
    public ResponseEntity<List<StockResponse>> getAllWatchlists() {
        logger.info("Received request to fetch all watchlists");
        try {

            List<Watchlist> watchlists = watchlistService.getAllWatchlists();
            // logger.info("Successfully fetched {} watchlists", watchlists.size());
            return ResponseEntity.ok(watchlists.stream().map(stockMapper::buildStockResponse).toList());
        } catch (Exception e) {
            logger.error("Error fetching watchlists: {}", e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
} 