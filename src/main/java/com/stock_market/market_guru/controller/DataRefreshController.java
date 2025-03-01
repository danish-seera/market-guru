package com.stock_market.market_guru.controller;

import com.stock_market.market_guru.service.SourceRefreshService;
import com.stock_market.market_guru.service.StockRefreshService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/refresh/")
public class DataRefreshController {
    
    private static final Logger logger = LoggerFactory.getLogger(DataRefreshController.class);
    
    @Autowired
    private SourceRefreshService sourceRefreshService;
    @Autowired 
    private StockRefreshService stockRefreshService;

    @GetMapping("source/{source}")
    public ResponseEntity<String> refreshStockSnapshot(@PathVariable String source) {
        logger.info("Received request to refresh stock snapshot for source: {}", source);
        try {
            if (source.equalsIgnoreCase("guru-focus")) {
                sourceRefreshService.refreshSourceSnapshot(source);
                logger.info("Successfully refreshed source snapshot for: {}", source);
                return ResponseEntity.ok("Success");
            }
            logger.warn("Invalid source provided: {}", source);
            return ResponseEntity.badRequest().body("Invalid source");
        } catch (Exception e) {
            logger.error("Error while refreshing source snapshot for {}: {}", source, e.getMessage());
            return ResponseEntity.internalServerError().body("Error occurred during refresh");
        }
    }

    @GetMapping("stock/{exchange}/{instrument}")
    public ResponseEntity<String> refreshStock(@PathVariable String exchange,
                                                     @PathVariable String instrument) {
        logger.info("Received request to refresh stock snapshot for exchange: {}, instrument: {}", 
                   exchange, instrument);
        try {
            stockRefreshService.refreshStock(exchange, instrument);
            logger.info("Successfully refreshed stock snapshot for {}/{}", exchange, instrument);
            return ResponseEntity.ok("Success");
        } catch (Exception e) {
            logger.error("Error while refreshing stock snapshot for {}/{}: {}", 
                        exchange, instrument, e.getMessage());
            return ResponseEntity.internalServerError().body("Error occurred during refresh");
        }
    }

    @GetMapping("stocks")
    public ResponseEntity<String> refreshStockSnapshot() {
        logger.info("Received request to refresh all stocks");
        try {
            stockRefreshService.refreshStockAll();
            logger.info("Successfully refreshed all stocks");
            return ResponseEntity.ok("Success");
        } catch (Exception e) {
            logger.error("Error while refreshing all stocks: {}", e.getMessage());
            return ResponseEntity.internalServerError().body("Error occurred during refresh");
        }
    }
}
