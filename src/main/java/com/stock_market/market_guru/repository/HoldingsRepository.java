package com.stock_market.market_guru.repository;

import com.stock_market.market_guru.entity.Holdings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HoldingsRepository extends JpaRepository<Holdings, Long> {
    List<Holdings> findAllByUserId(Integer userId);

} 