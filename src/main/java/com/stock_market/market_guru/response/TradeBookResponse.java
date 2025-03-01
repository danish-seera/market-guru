package com.stock_market.market_guru.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TradeBookResponse {
    @JsonProperty("trade_id")
    private Long tradeId;
    
    private String broker;
    
    private String exchange;
    
    private String instrument;
    
    @JsonProperty("company_name")
    private String companyName;
    
    @JsonProperty("trade_date")
    private LocalDateTime tradeDate;
    
    @JsonProperty("trade_type")
    private String tradeType;
    
    private Integer quantity;
    
    @JsonProperty("unit_price")
    private Integer unitPrice;
    
    @JsonProperty("total_value")
    private BigDecimal totalValue;
} 