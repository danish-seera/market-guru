package com.stock_market.market_guru.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HoldingsResponse {
    private Long id;
    private String broker;
    private String exchange;
    private String instrument;
    @JsonProperty("company_name")
    private String companyName;
    private Integer quantity;
    @JsonProperty("avg_price")
    private Integer avgPrice;
    @JsonProperty("investment_value")
    private BigDecimal investmentValue;
    @JsonProperty("current_price")
    private BigDecimal currentPrice;
    @JsonProperty("current_value")
    private BigDecimal currentValue;
    @JsonProperty("profit_loss")
    private BigDecimal profitLoss;
    @JsonProperty("profit_loss_percentage")
    private BigDecimal profitLossPercentage;
    @JsonProperty("created_at")
    private LocalDateTime createdAt;
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;
} 