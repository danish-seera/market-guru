package com.stock_market.market_guru.client.respone;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class General {
    private String company;
    @JsonProperty("gf_score")
    private String gfScore;
    @JsonProperty("rank_financial_strength")
    private String rankFinancialStrength;
    @JsonProperty("rank_profitability")
    private String rankProfitability;
    @JsonProperty("rank_growth")
    private String rankGrowth;
    @JsonProperty("rank_momentum")
    private String rankMomentum;
    private String group;
    private String subindustry;
    @JsonProperty("risk_assessment")
    private String riskAssessment;
    @JsonProperty("gf_valuation")
    private String gfValuation;
}
