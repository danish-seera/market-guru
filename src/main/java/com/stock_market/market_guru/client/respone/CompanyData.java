package com.stock_market.market_guru.client.respone;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
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
    private String ShillerPE;
    @JsonProperty("ShillerPE_med")
    private String shillerPEMed;
    @JsonProperty("valuation_avg")
    private String valuationAvg;
    @JsonProperty("gf_value")
    private String gfValue;
    @JsonProperty("gf_score")
    private int gfScore;
    @JsonProperty("gf_score_med")
    private int gfScoreMed;
}
