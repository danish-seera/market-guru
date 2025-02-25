package com.stock_market.market_guru.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "guruFocusClient", configuration = FeignClientConfig.class,
        url = "https://api.gurufocus.com/public/user")
public interface GuruFocusClient {

    @GetMapping("/{apiKey}/stock/{symbol}/{category}")
    Object getStockInfo(@PathVariable("apiKey") String apiKey,
                               @PathVariable("symbol") String symbol,
                                @PathVariable("category") String category);
}
