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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getGfScore() {
        return gfScore;
    }

    public void setGfScore(String gfScore) {
        this.gfScore = gfScore;
    }

    public String getRankFinancialStrength() {
        return rankFinancialStrength;
    }

    public void setRankFinancialStrength(String rankFinancialStrength) {
        this.rankFinancialStrength = rankFinancialStrength;
    }

    public String getRankProfitability() {
        return rankProfitability;
    }

    public void setRankProfitability(String rankProfitability) {
        this.rankProfitability = rankProfitability;
    }

    public String getRankGrowth() {
        return rankGrowth;
    }

    public void setRankGrowth(String rankGrowth) {
        this.rankGrowth = rankGrowth;
    }

    public String getRankMomentum() {
        return rankMomentum;
    }

    public void setRankMomentum(String rankMomentum) {
        this.rankMomentum = rankMomentum;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getSubindustry() {
        return subindustry;
    }

    public void setSubindustry(String subindustry) {
        this.subindustry = subindustry;
    }

    public String getRiskAssessment() {
        return riskAssessment;
    }

    public void setRiskAssessment(String riskAssessment) {
        this.riskAssessment = riskAssessment;
    }

    public String getGfValuation() {
        return gfValuation;
    }

    public void setGfValuation(String gfValuation) {
        this.gfValuation = gfValuation;
    }
}
