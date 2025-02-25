package com.stock_market.market_guru.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.stock_market.market_guru.client.GuruFocusClient;

import com.stock_market.market_guru.client.respone.CompanyData;
import com.stock_market.market_guru.client.respone.General;
import com.stock_market.market_guru.client.respone.GuruFocusSummaryResponse;
import com.stock_market.market_guru.entity.SourceDetail;
import com.stock_market.market_guru.entity.StockRawSnapshot;
import com.stock_market.market_guru.entity.Watchlist;
import com.stock_market.market_guru.repository.SourceDetailRepository;
import com.stock_market.market_guru.repository.StockRawSnapshotRepository;
import com.stock_market.market_guru.repository.WatchlistRepository;
import io.micrometer.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class GuruFocusService {

  private final Logger log = LoggerFactory.getLogger( GuruFocusService.class );

  @Autowired private ObjectMapper mapper;
  @Autowired private GuruFocusClient guruFocusClient;
  @Autowired private WatchlistRepository watchlistRepository;
  @Autowired private SourceDetailRepository sourceDetailRepository;
  @Autowired private StockRawSnapshotRepository stockRawSnapshotRepository;

  @Async
  public void refreshSnapshot() {
    log.info("Starting refreshSnapshot process");

    List<Watchlist> stocks = watchlistRepository.findAll();
    List<SourceDetail> sourceDetails = sourceDetailRepository.findBySourceName("guru-focus");

    for (Watchlist stock : stocks) {
      for (SourceDetail sourceDetail : sourceDetails) {
        // Check if the source detail was last refreshed more than 7 days ago
        if (LocalDateTime.now().minusDays(7).isAfter(sourceDetail.getLastRefreshed())) {
          String stockName = stock.getExchange() + ":" + stock.getInstrument();
          log.info("Processing stock {} with source {} and category {}", stockName,
                  sourceDetail.getSourceName(), sourceDetail.getCategory());

          try {
            Object response = guruFocusClient.getStockInfo(
                    sourceDetail.getToken(),
                    stockName,
                    sourceDetail.getCategory()
            );

            if (response != null) {
              String jsonResponse = new Gson().toJson(response);
              StockRawSnapshot stockRawSnapshot = buildStockRawSnapshot(
                      stock.getExchange(),
                      stock.getInstrument(),
                      sourceDetail.getSourceName(),
                      sourceDetail.getCategory(),
                      jsonResponse
              );
              stockRawSnapshotRepository.saveAndFlush(stockRawSnapshot);

              GuruFocusSummaryResponse guruFocusSummaryResponse =
                      mapper.readValue(jsonResponse, GuruFocusSummaryResponse.class);

              if (guruFocusSummaryResponse != null) {
                populateStockData(stock, guruFocusSummaryResponse);
                watchlistRepository.saveAndFlush(stock);
                log.info("Successfully updated stock {} with the latest snapshot", stockName);
              } else {
                log.warn("Parsed GuruFocusSummaryResponse is null for stock {}", stockName);
              }
            } else {
              log.warn("Received null response from {}:{} for stock {}",
                      sourceDetail.getSourceName(), sourceDetail.getCategory(), stockName);
            }
          } catch (Exception e) {
            log.error("Error processing {} API (category: {}) for stock {}: {}",
                    sourceDetail.getSourceName(), sourceDetail.getCategory(), stockName, e.getMessage(), e);
          }
        } else {
          log.info("Skipping {} because last refreshed at {} is within the past 7 days",
                  stock.getInstrument(), sourceDetail.getLastRefreshed());
        }
      }
    }
    log.info("Completed refreshSnapshot process");
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

  private void populateStockData(
      Watchlist stock, GuruFocusSummaryResponse guruFocusSummaryResponse) {
    General general = guruFocusSummaryResponse.getSummary().getGeneral();
    CompanyData companyData = guruFocusSummaryResponse.getSummary().getCompanyData();
    stock.setCompanyName(general.getCompany());
    stock.setGroupName(general.getGroup());
    stock.setSubIndustry(general.getSubindustry());

    stock.setGfValue(getBigDecimal(companyData.getGfValue()));
    stock.setGfScoreMed(companyData.getGfScoreMed());
    stock.setGfScore(companyData.getGfScore());
    stock.setGfRiskAssessment(general.getRiskAssessment());
    stock.setGfRemark(general.getGfValuation());
    stock.setGfMomentum(Integer.valueOf(general.getRankMomentum()));
    stock.setValuationAvg(getBigDecimal(companyData.getValuationAvg()));

    stock.setMktCapital(getBigDecimal(companyData.getMktcap()));
    stock.setPrice52wHigh(getBigDecimal(companyData.getPrice52whigh()));
    stock.setPrice3yHigh(getBigDecimal(companyData.getPrice3yhigh()));
    stock.setPrice5yHigh(getBigDecimal(companyData.getPrice5yhigh()));
    stock.setPrice10yHigh(getBigDecimal(companyData.getPrice10yhigh()));
    stock.setPrice52wLow(getBigDecimal(companyData.getPrice52wlow()));
    stock.setPrice3yLow(getBigDecimal(companyData.getPrice3ylow()));
    stock.setPrice5yLow(getBigDecimal(companyData.getPrice5ylow()));
    stock.setPrice10yLow(getBigDecimal(companyData.getPrice10ylow()));
    stock.setBeta(getBigDecimal(companyData.getBeta()));
    stock.setShillerPE(getBigDecimal(companyData.getShillerPE()));
    stock.setShillerPEMed(getBigDecimal(companyData.getShillerPEMed()));
  }

  private BigDecimal getBigDecimal(String number) {
    if (StringUtils.isNotEmpty(number))
      return new BigDecimal(number);
    else return null;
  }
}
