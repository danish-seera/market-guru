package com.stock_market.market_guru.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stock_market.market_guru.client.respone.CompanyData;
import com.stock_market.market_guru.client.respone.General;
import com.stock_market.market_guru.client.respone.GuruFocusSummaryResponse;
import com.stock_market.market_guru.entity.Watchlist;
import com.stock_market.market_guru.repository.StockRawSnapshotRepository;
import com.stock_market.market_guru.repository.WatchlistRepository;
import io.micrometer.common.util.StringUtils;
import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockRefreshService {

  private final Logger log = LoggerFactory.getLogger(StockRefreshService.class);

  @Autowired private ObjectMapper mapper;
  @Autowired private WatchlistRepository watchlistRepository;
  @Autowired private StockRawSnapshotRepository stockRawSnapshotRepository;

  public void refreshStockAll() {
    log.info("Starting full stock refresh for all stocks");
    var stocks = watchlistRepository.findAll();
    stocks.forEach(this::refreshStock);
    log.info("Completed full stock refresh for all stocks");
  }

  public void refreshStock(String exchange, String instrument) {
    log.info("Refreshing stock for exchange: {} and instrument: {}", exchange, instrument);
    watchlistRepository.findByExchangeAndInstrument(exchange, instrument)
            .ifPresentOrElse(
                    this::refreshStock,
                    () -> log.warn("No stock found for exchange: {} and instrument: {}", exchange, instrument)
            );
  }

  private void refreshStock(Watchlist stock) {
    var snapshots = stockRawSnapshotRepository.findByExchangeAndInstrument(stock.getExchange(), stock.getInstrument());
    if (snapshots.isEmpty()) {
      log.warn("No snapshots found for stock {}:{}", stock.getExchange(), stock.getInstrument());
      return;
    }
    snapshots.stream()
            .filter(snapshot -> "guru-focus".equals(snapshot.getSource()) && "summary".equals(snapshot.getCategory()))
            .forEach(snapshot -> processGuruFocusFields(stock, snapshot.getResponseData()));
  }

  private void processGuruFocusFields(Watchlist stock, String jsonResponse) {
    try {
      var guruResponse = mapper.readValue(jsonResponse, GuruFocusSummaryResponse.class);
      if (guruResponse != null) {
        populateStockData(stock, guruResponse);
        watchlistRepository.saveAndFlush(stock);
        log.info("Successfully updated stock {} with the latest snapshot of guru-focus", stock.getInstrument());
      } else {
        log.warn("Parsed GuruFocusSummaryResponse is null for stock {}", stock.getInstrument());
      }
    } catch (JsonProcessingException e) {
      log.error("Error processing snapshot for stock {}: {}", stock.getInstrument(), e.getMessage(), e);
    }
  }

  private void populateStockData(
      Watchlist stock, GuruFocusSummaryResponse guruFocusSummaryResponse) {
    General general = guruFocusSummaryResponse.getSummary().getGeneral();
    CompanyData companyData = guruFocusSummaryResponse.getSummary().getCompanyData();
    stock.setCompanyName(general.getCompany());
    stock.setLastTradePrice(getBigDecimal(general.getPrice()));
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

  public static BigDecimal getBigDecimal(String number) {
    if (StringUtils.isNotEmpty(number)) return new BigDecimal(number);
    else return null;
  }
}
