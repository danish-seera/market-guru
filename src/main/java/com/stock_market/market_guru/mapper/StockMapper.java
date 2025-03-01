package com.stock_market.market_guru.mapper;

import com.stock_market.market_guru.entity.Watchlist;
import com.stock_market.market_guru.response.StockResponse;
import io.micrometer.common.util.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.springframework.web.bind.annotation.Mapping;

import java.util.Optional;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StockMapper {

    StockResponse buildStockResponse(Watchlist watchlist);

}

