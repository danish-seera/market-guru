package com.stock_market.market_guru.repository;

import com.stock_market.market_guru.entity.SourceDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SourceDetailRepository extends JpaRepository<SourceDetail, Integer> {
    // Custom methods
    List<SourceDetail> findBySourceName(String sourceName);
    SourceDetail findBySourceNameAndCategory(String sourceName, String category);
}
