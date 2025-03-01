package com.stock_market.market_guru.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.stock_market.market_guru.entity.SourceDetail;
import com.stock_market.market_guru.entity.Watchlist;
import com.stock_market.market_guru.repository.SourceDetailRepository;
import com.stock_market.market_guru.repository.WatchlistRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class StockPriceFetcher {

  @Autowired private WatchlistRepository watchlistRepository;
  @Autowired private SourceDetailRepository sourceDetailRepository;

  // Run every 5 minutes (fixedRate = 5 * 60 * 1000 milliseconds)
//  @Scheduled(fixedRate = 5 * 60 * 1000)
  public void fetchStockPrices() {
    SourceDetail sourceDetail = sourceDetailRepository.findBySourceNameAndCategory("alpha-vantage", "GLOBAL_QUOTE");
    try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
      for (Watchlist stock : watchlistRepository.findAll()) {
        String symbol = stock.getInstrument() + "." + (stock.getExchange().equals("NSE") ? "BSE" : stock.getExchange());
        String apiUrl =
            String.format(
                "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=%s&apikey=%s",
                symbol, sourceDetail.getToken());

        HttpGet request = new HttpGet(apiUrl);
        try (CloseableHttpResponse response = httpClient.execute(request)) {
          String jsonResponse = EntityUtils.toString(response.getEntity());
          JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();
          JsonObject globalQuote = jsonObject.getAsJsonObject("Global Quote");
          JsonElement priceElement = globalQuote.get("05. price");
          if (priceElement == null) {
            System.out.printf("%s : Price not available \n", symbol);
          } else {
            String price = priceElement.getAsString();
            stock.setLastTradePrice(StockRefreshService.getBigDecimal(price));
            watchlistRepository.saveAndFlush(
                    stock);
          }
        }
      }
    } catch (IOException e) {
      System.err.println("Error: " + e.getMessage());
    }
  }
}
