package com.stock_market.market_guru.repository;

import com.stock_market.market_guru.entity.Watchlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WatchlistRepository extends JpaRepository<Watchlist, String> {
    Optional<Watchlist> findByExchangeAndInstrument(String exchange, String instrument);
}
