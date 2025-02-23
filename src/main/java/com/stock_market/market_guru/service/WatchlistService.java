package com.stock_market.market_guru.service;

import com.stock_market.market_guru.client.respone.CompanyData;
import com.stock_market.market_guru.client.respone.General;
import com.stock_market.market_guru.client.respone.GuruFocusSummaryResponse;
import com.stock_market.market_guru.entity.Watchlist;
import com.stock_market.market_guru.repository.WatchlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class WatchlistService {

  @Autowired private GuruFocusService guruFocusService;
  @Autowired private WatchlistRepository watchlistRepository;

  public List<Watchlist> getAllWatchlist() {
    return watchlistRepository.findAll();
  }

  public Optional<Watchlist> getWatchlistByInstrument(String instrument) {
    return watchlistRepository.findById(instrument);
  }

  @Async
  public void refreshWatchlist() {
    List<Watchlist> stocks = getAllWatchlist();
    for (Watchlist stock : stocks) {
      if (LocalDateTime.now().minusSeconds(7).isAfter(stock.getCreatedAt()))
        refreshStockData(stock);
      else
        System.out.println("Skipping " + stock.getInstrument() + " as it was last updated less than 7 days ago.");
    }
  }

  private void refreshStockData(Watchlist stock) {
    String stockName = stock.getExchange() + ":" + stock.getInstrument();
    try {
      GuruFocusSummaryResponse guruFocusSummaryResponse = guruFocusService.getStockSummary(stockName);
      if (guruFocusSummaryResponse != null) {
        populateStockData(stock, guruFocusSummaryResponse);
        watchlistRepository.saveAndFlush(stock);
      }
    } catch (Exception e) {
      System.out.println("Error while refreshing stock:" + stockName + "Exception " + e.getMessage());
    }
  }

  private void populateStockData(Watchlist stock, GuruFocusSummaryResponse guruFocusSummaryResponse) {
    General general = guruFocusSummaryResponse.getSummary().getGeneral();
    CompanyData companyData = guruFocusSummaryResponse.getSummary().getCompanyData();
    stock.setCompanyName(general.getCompany());
    stock.setGroupName(general.getGroup());
    stock.setSubIndustry(general.getSubindustry());

    stock.setGfValue(new BigDecimal(companyData.getGfValue()));
    stock.setGfScoreMed(companyData.getGfScoreMed());
    stock.setGfScore(companyData.getGfScore());
    stock.setGfRiskAssessment(general.getRiskAssessment());
    stock.setGfRemark(general.getGfValuation());
    stock.setGfMomentum(Integer.valueOf(general.getRankMomentum()));
    stock.setValuationAvg(new BigDecimal(companyData.getValuationAvg()));

    stock.setMktCapital(new BigDecimal(companyData.getMktcap()));
    stock.setPrice52wHigh(new BigDecimal(companyData.getPrice52whigh()));
    stock.setPrice3yHigh(new BigDecimal(companyData.getPrice3yhigh()));
    stock.setPrice5yHigh(new BigDecimal(companyData.getPrice5yhigh()));
    stock.setPrice10yHigh(new BigDecimal(companyData.getPrice10yhigh()));
    stock.setPrice52wLow(new BigDecimal(companyData.getPrice52wlow()));
    stock.setPrice3yLow(new BigDecimal(companyData.getPrice3ylow()));
    stock.setPrice5yLow(new BigDecimal(companyData.getPrice5ylow()));
    stock.setPrice10yLow(new BigDecimal(companyData.getPrice10ylow()));
    stock.setBeta(new BigDecimal(companyData.getBeta()));
    stock.setShillerPE(new BigDecimal(companyData.getShillerPE()));
    stock.setShillerPEMed(new BigDecimal(companyData.getShillerPEMed()));
  }
}
