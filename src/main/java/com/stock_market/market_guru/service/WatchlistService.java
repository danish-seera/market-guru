package com.stock_market.market_guru.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.stock_market.market_guru.client.respone.CompanyData;
import com.stock_market.market_guru.client.respone.General;
import com.stock_market.market_guru.client.respone.GuruFocusSummaryResponse;
import com.stock_market.market_guru.entity.StockRawSnapshot;
import com.stock_market.market_guru.entity.Watchlist;
import com.stock_market.market_guru.repository.StockRawSnapshotRepository;
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


  @Autowired private WatchlistRepository watchlistRepository;

  public List<Watchlist> getAllWatchlist() {
    return watchlistRepository.findAll();
  }

  public Optional<Watchlist> getWatchlistByInstrument(String instrument) {
    return watchlistRepository.findById(instrument);
  }
}
