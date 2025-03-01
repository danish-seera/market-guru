package com.stock_market.market_guru.mapper;



import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import com.stock_market.market_guru.entity.TradeBook;
import com.stock_market.market_guru.response.TradeBookResponse;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TradeBookMapper {
    TradeBookResponse buildTradeResponse(TradeBook tradeBook);
}