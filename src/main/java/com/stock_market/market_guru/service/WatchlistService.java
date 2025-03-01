package com.stock_market.market_guru.service;

import com.stock_market.market_guru.entity.Watchlist;
import java.util.List;
import java.util.Optional;

public interface WatchlistService {
    Optional<Watchlist> getWatchlistByExchangeAndInstrument(String exchange, String instrument);
    List<Watchlist> getAllWatchlists();
} 