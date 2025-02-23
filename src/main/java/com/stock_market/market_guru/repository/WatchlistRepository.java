package com.stock_market.market_guru.repository;

import com.stock_market.market_guru.entity.Watchlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WatchlistRepository extends JpaRepository<Watchlist, String> {
    // 'instrument' is the primary key (of type String)
}
