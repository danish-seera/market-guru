package com.stock_market.market_guru.service.impl;

import com.stock_market.market_guru.entity.Watchlist;
import com.stock_market.market_guru.repository.WatchlistRepository;
import com.stock_market.market_guru.service.WatchlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WatchlistServiceImpl implements WatchlistService {

    @Autowired
    private WatchlistRepository watchlistRepository;

    @Override
    @Cacheable(value = "watchlist-1", key = "#exchange + '-' + #instrument")
    public Optional<Watchlist> getWatchlistByExchangeAndInstrument(String exchange, String instrument) {
        return watchlistRepository.findByExchangeAndInstrument(exchange, instrument);
    }

    @Override
    @Cacheable(value = "watchlist-2")
    public List<Watchlist> getAllWatchlists() {
        return watchlistRepository.findAll();
    }

} 