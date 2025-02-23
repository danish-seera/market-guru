package com.stock_market.market_guru.service;

import com.stock_market.market_guru.client.respone.CompanyData;
import com.stock_market.market_guru.client.respone.General;
import com.stock_market.market_guru.client.respone.GuruFocusSummaryResponse;
import com.stock_market.market_guru.entity.Watchlist;
import com.stock_market.market_guru.repository.WatchlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class WatchlistService {

    @Autowired
    private GuruFocusService guruFocusService;
    @Autowired
    private WatchlistRepository watchlistRepository;

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
            if (LocalDateTime.now().minusDays(7).isAfter(stock.getCreatedAt())) {
                String stockName = stock.getExchange() + ":" + stock.getInstrument();
                GuruFocusSummaryResponse guruFocusSummaryResponse = guruFocusService.getStockSummary(stockName);
                if (guruFocusSummaryResponse != null) {
                    General general = guruFocusSummaryResponse.getSummary().getGeneral();
                    CompanyData companyData = guruFocusSummaryResponse.getSummary().getCompanyData();
                    stock.setCompanyName(general.getCompany());
                    stock.setGroupName(general.getGroup());
                    stock.setSubIndustry(general.getSubindustry());

                    stock.setGfValue(Double.valueOf(companyData.getGfValue()));
                    stock.setGfScoreMed(companyData.getGfScoreMed());
                    stock.setGfScore(companyData.getGfScore());
                    stock.setGfRiskAssessment(general.getRiskAssessment());
                    stock.setGfRemark(general.getGfValuation());
                    stock.setGfMomentum(Integer.valueOf(general.getRankMomentum()));
                    stock.setValuationAvg(Double.valueOf(companyData.getValuationAvg()));


                    stock.setMktCapital(Double.valueOf(companyData.getMktcap()));
                    stock.setPrice52wHigh(Double.valueOf(companyData.getPrice52whigh()));
                    stock.setPrice3yHigh(Double.valueOf(companyData.getPrice3yhigh()));
                    stock.setPrice5yHigh(Double.valueOf(companyData.getPrice5yhigh()));
                    stock.setPrice10yHigh(Double.valueOf(companyData.getPrice10yhigh()));
                    stock.setPrice52wLow(Double.valueOf(companyData.getPrice52wlow()));
                    stock.setPrice3yLow(Double.valueOf(companyData.getPrice3ylow()));
                    stock.setPrice5yLow(Double.valueOf(companyData.getPrice5ylow()));
                    stock.setPrice10yLow(Double.valueOf(companyData.getPrice10ylow()));
                    stock.setBeta(Double.valueOf(companyData.getBeta()));
                    stock.setShillerPE(Double.valueOf(companyData.getShillerPE()));
                    stock.setShillerPEMed(Double.valueOf(companyData.getShillerPEMed()));
                }
            } else {
                System.out.println("Skipping " + stock.getInstrument() + " as it was last updated less than 7 days ago.");
            }
        }
    }
}
