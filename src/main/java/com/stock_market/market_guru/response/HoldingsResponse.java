package com.stock_market.market_guru.response;

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
public class HoldingsResponse {
    private Long id;
    private String broker;
    private String exchange;
    private String instrument;
    private String companyName;
    private Integer quantity;
    private Integer avgPrice;
    private BigDecimal investmentValue;
    private BigDecimal currentPrice;
    private BigDecimal currentValue;
    private BigDecimal profitLoss;
    private BigDecimal profitLossPercentage;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
} 