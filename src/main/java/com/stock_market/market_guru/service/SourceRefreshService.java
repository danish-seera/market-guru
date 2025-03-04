package com.stock_market.market_guru.service;

import com.google.gson.Gson;
import com.stock_market.market_guru.client.GuruFocusClient;

import com.stock_market.market_guru.entity.SourceDetail;
import com.stock_market.market_guru.entity.StockRawSnapshot;
import com.stock_market.market_guru.entity.Watchlist;
import com.stock_market.market_guru.repository.SourceDetailRepository;
import com.stock_market.market_guru.repository.StockRawSnapshotRepository;
import com.stock_market.market_guru.repository.WatchlistRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SourceRefreshService {

  private final Logger log = LoggerFactory.getLogger(SourceRefreshService.class);

//  @Autowired private ObjectMapper mapper;
  @Autowired private GuruFocusClient guruFocusClient;
  @Autowired private WatchlistRepository watchlistRepository;
  @Autowired private SourceDetailRepository sourceDetailRepository;
  @Autowired private StockRawSnapshotRepository stockRawSnapshotRepository;

  @Async
  public void refreshSourceSnapshot(String source) {
    log.info("starting refresh-snapshot process for source:{}", source);

    var sourceDetails = sourceDetailRepository.findBySourceName(source);
    var watchlistStocks = watchlistRepository.findAll();

    sourceDetails.forEach(sourceDetail ->
            watchlistStocks.forEach(stock -> {
              var stockName = stock.getExchange() + ":" + stock.getInstrument();
              var snapshotOpt = stockRawSnapshotRepository
                      .findByExchangeAndInstrumentAndSourceAndCategory(
                              stock.getExchange(),
                              stock.getInstrument(),
                              sourceDetail.getSourceName(),
                              sourceDetail.getCategory()
                      );
              if (snapshotOpt.isEmpty() ||
                      snapshotOpt.get().getUpdatedAt().isBefore(LocalDateTime.now().minusDays(7))) {
                log.info("processing stock:{} with source:{} and category:{}",
                        stockName,
                        sourceDetail.getSourceName(),
                        sourceDetail.getCategory());
                processStock(stock, sourceDetail);
              } else {
                log.info("skipping stock:{} with source:{} and category:{} as it was refreshed less than 7 days ago",
                        stockName,
                        sourceDetail.getSourceName(),
                        sourceDetail.getCategory());
              }
            })
    );

    log.info("completed refresh-snapshot process for source:{}", source);
  }

  private void processStock(Watchlist stock, SourceDetail sourceDetail) {
    var stockName = stock.getExchange() + ":" + stock.getInstrument();
    try {
      var response = guruFocusClient.getStockInfo(
              sourceDetail.getToken(), stockName, sourceDetail.getCategory());
      if (response != null) {
        var jsonResponse = new Gson().toJson(response);
        var stockRawSnapshot = buildStockRawSnapshot(
                stock.getExchange(),
                stock.getInstrument(),
                sourceDetail.getSourceName(),
                sourceDetail.getCategory(),
                jsonResponse);
        stockRawSnapshotRepository.saveAndFlush(stockRawSnapshot);
      } else {
        log.warn("received null response from {}:{} for stock;{}",
                sourceDetail.getSourceName(),
                sourceDetail.getCategory(),
                stockName);
      }
    } catch (Exception e) {
      log.error("Error processing source:{} API category:{} for stock:{}: exception:{}",
              sourceDetail.getSourceName(),
              sourceDetail.getCategory(),
              stockName,
              e.getMessage(),
              e);
    }
  }

  private StockRawSnapshot buildStockRawSnapshot(
      String exchange, String instrument, String source, String category, String response) {
    StockRawSnapshot stockRawSnapshot = new StockRawSnapshot();
    stockRawSnapshot.setExchange(exchange);
    stockRawSnapshot.setInstrument(instrument);
    stockRawSnapshot.setSource(source);
    stockRawSnapshot.setCategory(category);
    stockRawSnapshot.setResponseData(response);
    return stockRawSnapshot;
  }
}
