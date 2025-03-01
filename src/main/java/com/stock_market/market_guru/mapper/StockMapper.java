package com.stock_market.market_guru.mapper;

import com.stock_market.market_guru.entity.Watchlist;
import com.stock_market.market_guru.response.StockResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StockMapper {
    StockResponse buildStockResponse(Watchlist watchlist);
}