package com.stock_market.market_guru.mapper;

import com.stock_market.market_guru.entity.Holdings;
import com.stock_market.market_guru.response.HoldingsResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HoldingsMapper {
    HoldingsResponse buildHoldingsResponse(Holdings holdings);
} 