package com.stock_market.market_guru.controller;

import com.stock_market.market_guru.service.SourceRefreshService;
import com.stock_market.market_guru.service.StockRefreshService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/refresh/")
public class DataRefreshController {

    @Autowired
    private SourceRefreshService sourceRefreshService;
    @Autowired
    private StockRefreshService stockRefreshService;

    @GetMapping("source/{source}")
    public String refreshSourceSnapshot(@PathVariable String source) {
        if (source.equalsIgnoreCase("guru-focus"))
            sourceRefreshService.refreshSourceSnapshot(source);
        return "Success";
    }

    @GetMapping("stock/{exchange}/{instrument}")
    public String refreshStockSnapshot(@PathVariable String exchange, @PathVariable String instrument) {
        stockRefreshService.refreshStockSnapshot(exchange, instrument);
        return "Success";
    }

    @GetMapping("stocks")
    public String refreshStockSnapshot() {
        stockRefreshService.refreshStockAll();
        return "Success";
    }
}
