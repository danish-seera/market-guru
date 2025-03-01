package com.stock_market.market_guru.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StockResponse {
    // Basic Details
    @JsonProperty("exchange") private String exchange;
    @JsonProperty("instrument") private String instrument;
    @JsonProperty("company_name") private String companyName;
    @JsonProperty("last_trade_price") private double lastTradePrice;

    // Valuation & Scores
    @JsonProperty("gf_value") private double gfValue;
    @JsonProperty("valuation_avg") private double valuationAvg;
    @JsonProperty("gf_risk_assessment") private String gfRiskAssessment;
    @JsonProperty("gf_remark") private String gfRemark;
    @JsonProperty("gf_score") private int gfScore;
    @JsonProperty("gf_momentum") private int gfMomentum;

    // Market Data
    @JsonProperty("mkt_capital") private long mktCapital;
    @JsonProperty("sub_industry") private String subIndustry;

    // Price Ranges (52-week, 3y, 5y, 10y)
    @JsonProperty("price_52w_low") private double price52wLow;
    @JsonProperty("price_52w_high") private double price52wHigh;
    @JsonProperty("price_3y_low") private double price3yLow;
    @JsonProperty("price_3y_high") private double price3yHigh;
    @JsonProperty("price_5y_low") private double price5yLow;
    @JsonProperty("price_5y_high") private double price5yHigh;
    @JsonProperty("price_10y_low") private double price10yLow;
    @JsonProperty("price_10y_high") private double price10yHigh;

    // Advanced Metrics
    @JsonProperty("beta") private double beta;
    @JsonProperty("shiller_pe") private double shillerPe;
    @JsonProperty("shiller_pe_med") private double shillerPeMed;
    @JsonProperty("group_name") private String groupName;
    @JsonProperty("gf_score_med") private int gfScoreMed;

    // Default Constructor (JSON parsers ke liye zaroori hai)
    public StockResponse() {}

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getInstrument() {
        return instrument;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public double getLastTradePrice() {
        return lastTradePrice;
    }

    public void setLastTradePrice(double lastTradePrice) {
        this.lastTradePrice = lastTradePrice;
    }

    public double getGfValue() {
        return gfValue;
    }

    public void setGfValue(double gfValue) {
        this.gfValue = gfValue;
    }

    public double getValuationAvg() {
        return valuationAvg;
    }

    public void setValuationAvg(double valuationAvg) {
        this.valuationAvg = valuationAvg;
    }

    public String getGfRiskAssessment() {
        return gfRiskAssessment;
    }

    public void setGfRiskAssessment(String gfRiskAssessment) {
        this.gfRiskAssessment = gfRiskAssessment;
    }

    public String getGfRemark() {
        return gfRemark;
    }

    public void setGfRemark(String gfRemark) {
        this.gfRemark = gfRemark;
    }

    public int getGfScore() {
        return gfScore;
    }

    public void setGfScore(int gfScore) {
        this.gfScore = gfScore;
    }

    public int getGfMomentum() {
        return gfMomentum;
    }

    public void setGfMomentum(int gfMomentum) {
        this.gfMomentum = gfMomentum;
    }

    public long getMktCapital() {
        return mktCapital;
    }

    public void setMktCapital(long mktCapital) {
        this.mktCapital = mktCapital;
    }

    public String getSubIndustry() {
        return subIndustry;
    }

    public void setSubIndustry(String subIndustry) {
        this.subIndustry = subIndustry;
    }

    public double getPrice52wLow() {
        return price52wLow;
    }

    public void setPrice52wLow(double price52wLow) {
        this.price52wLow = price52wLow;
    }

    public double getPrice52wHigh() {
        return price52wHigh;
    }

    public void setPrice52wHigh(double price52wHigh) {
        this.price52wHigh = price52wHigh;
    }

    public double getPrice3yLow() {
        return price3yLow;
    }

    public void setPrice3yLow(double price3yLow) {
        this.price3yLow = price3yLow;
    }

    public double getPrice3yHigh() {
        return price3yHigh;
    }

    public void setPrice3yHigh(double price3yHigh) {
        this.price3yHigh = price3yHigh;
    }

    public double getPrice5yLow() {
        return price5yLow;
    }

    public void setPrice5yLow(double price5yLow) {
        this.price5yLow = price5yLow;
    }

    public double getPrice5yHigh() {
        return price5yHigh;
    }

    public void setPrice5yHigh(double price5yHigh) {
        this.price5yHigh = price5yHigh;
    }

    public double getPrice10yLow() {
        return price10yLow;
    }

    public void setPrice10yLow(double price10yLow) {
        this.price10yLow = price10yLow;
    }

    public double getPrice10yHigh() {
        return price10yHigh;
    }

    public void setPrice10yHigh(double price10yHigh) {
        this.price10yHigh = price10yHigh;
    }

    public double getBeta() {
        return beta;
    }

    public void setBeta(double beta) {
        this.beta = beta;
    }

    public double getShillerPe() {
        return shillerPe;
    }

    public void setShillerPe(double shillerPe) {
        this.shillerPe = shillerPe;
    }

    public double getShillerPeMed() {
        return shillerPeMed;
    }

    public void setShillerPeMed(double shillerPeMed) {
        this.shillerPeMed = shillerPeMed;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getGfScoreMed() {
        return gfScoreMed;
    }

    public void setGfScoreMed(int gfScoreMed) {
        this.gfScoreMed = gfScoreMed;
    }
}
