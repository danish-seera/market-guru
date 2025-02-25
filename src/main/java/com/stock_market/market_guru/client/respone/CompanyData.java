package com.stock_market.market_guru.client.respone;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CompanyData {
    private String mktcap;
    @JsonProperty("price52wlow")
    private String price52wlow;
    @JsonProperty("price52whigh")
    private String price52whigh;
    @JsonProperty("price3ylow")
    private String price3ylow;
    @JsonProperty("price3yhigh")
    private String price3yhigh;
    @JsonProperty("price5ylow")
    private String price5ylow;
    @JsonProperty("price5yhigh")
    private String price5yhigh;
    @JsonProperty("price10yhigh")
    private String price10yhigh;
    @JsonProperty("price10ylow")
    private String price10ylow;
    private String beta;
    @JsonProperty("ShillerPE")
    private String ShillerPE;
    @JsonProperty("ShillerPE_med")
    private String shillerPEMed;
    @JsonProperty("valuation_avg")
    private String valuationAvg;
    @JsonProperty("gf_value")
    private String gfValue;
    @JsonProperty("gf_score")
    private Integer gfScore;
    @JsonProperty("gf_score_med")
    private Integer gfScoreMed;

    public String getMktcap() {
        return mktcap;
    }

    public void setMktcap(String mktcap) {
        this.mktcap = mktcap;
    }

    public String getPrice52wlow() {
        return price52wlow;
    }

    public void setPrice52wlow(String price52wlow) {
        this.price52wlow = price52wlow;
    }

    public String getPrice52whigh() {
        return price52whigh;
    }

    public void setPrice52whigh(String price52whigh) {
        this.price52whigh = price52whigh;
    }

    public String getPrice3ylow() {
        return price3ylow;
    }

    public void setPrice3ylow(String price3ylow) {
        this.price3ylow = price3ylow;
    }

    public String getPrice3yhigh() {
        return price3yhigh;
    }

    public void setPrice3yhigh(String price3yhigh) {
        this.price3yhigh = price3yhigh;
    }

    public String getPrice5ylow() {
        return price5ylow;
    }

    public void setPrice5ylow(String price5ylow) {
        this.price5ylow = price5ylow;
    }

    public String getPrice5yhigh() {
        return price5yhigh;
    }

    public void setPrice5yhigh(String price5yhigh) {
        this.price5yhigh = price5yhigh;
    }

    public String getPrice10yhigh() {
        return price10yhigh;
    }

    public void setPrice10yhigh(String price10yhigh) {
        this.price10yhigh = price10yhigh;
    }

    public String getPrice10ylow() {
        return price10ylow;
    }

    public void setPrice10ylow(String price10ylow) {
        this.price10ylow = price10ylow;
    }

    public String getBeta() {
        return beta;
    }

    public void setBeta(String beta) {
        this.beta = beta;
    }

    public String getShillerPE() {
        return ShillerPE;
    }

    public void setShillerPE(String shillerPE) {
        ShillerPE = shillerPE;
    }

    public String getShillerPEMed() {
        return shillerPEMed;
    }

    public void setShillerPEMed(String shillerPEMed) {
        this.shillerPEMed = shillerPEMed;
    }

    public String getValuationAvg() {
        return valuationAvg;
    }

    public void setValuationAvg(String valuationAvg) {
        this.valuationAvg = valuationAvg;
    }

    public String getGfValue() {
        return gfValue;
    }

    public void setGfValue(String gfValue) {
        this.gfValue = gfValue;
    }

    public Integer getGfScore() {
        return gfScore;
    }

    public void setGfScore(Integer gfScore) {
        this.gfScore = gfScore;
    }

    public Integer getGfScoreMed() {
        return gfScoreMed;
    }

    public void setGfScoreMed(Integer gfScoreMed) {
        this.gfScoreMed = gfScoreMed;
    }
}
