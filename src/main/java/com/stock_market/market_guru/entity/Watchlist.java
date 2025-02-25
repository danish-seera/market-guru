package com.stock_market.market_guru.entity;

import lombok.Data;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "watchlist")
public class Watchlist {

    @Column(name = "exchange", length = 16, nullable = false)
    private String exchange;

    @Id
    @Column(name = "instrument", length = 45, nullable = false)
    private String instrument;

    @Column(name = "company_name", length = 255)
    private String companyName;

    // "group" is a reserved keyword; use backticks in the column name mapping
    @Column(name = "`group`", length = 100)
    private String groupName;

    @Column(name = "sub_industry", length = 100)
    private String subIndustry;

    @Column(name = "mkt_capital", precision = 20, scale = 4)
    private BigDecimal mktCapital;

    @Column(name = "gf_score")
    private Integer gfScore;

    @Column(name = "gf_momentum")
    private Integer gfMomentum;

    @Column(name = "gf_risk_assessment", length = 255)
    private String gfRiskAssessment;

    @Column(name = "gf_remark", length = 50)
    private String gfRemark;

    @Column(name = "price_52w_low", precision = 15, scale = 4)
    private BigDecimal price52wLow;

    @Column(name = "price_52w_high", precision = 15, scale = 4)
    private BigDecimal price52wHigh;

    @Column(name = "price_3y_low", precision = 15, scale = 4)
    private BigDecimal price3yLow;

    @Column(name = "price_3y_high", precision = 15, scale = 4)
    private BigDecimal price3yHigh;

    @Column(name = "price_5y_low", precision = 15, scale = 4)
    private BigDecimal price5yLow;

    @Column(name = "price_5y_high", precision = 15, scale = 4)
    private BigDecimal price5yHigh;

    @Column(name = "price_10y_high", precision = 15, scale = 4)
    private BigDecimal price10yHigh;

    @Column(name = "price_10y_low", precision = 15, scale = 4)
    private BigDecimal price10yLow;

    @Column(name = "beta", precision = 10, scale = 4)
    private BigDecimal beta;

    @Column(name = "shiller_pe", precision = 15, scale = 4)
    private BigDecimal shillerPE;

    @Column(name = "shiller_pe_med", precision = 15, scale = 4)
    private BigDecimal shillerPEMed;

    @Column(name = "valuation_avg", precision = 15, scale = 4)
    private BigDecimal valuationAvg;

    @Column(name = "gf_value", precision = 15, scale = 4)
    private BigDecimal gfValue;

    @Column(name = "gf_score_med")
    private Integer gfScoreMed;

    @Column(name = "created_at", nullable = false, columnDefinition = "datetime default CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false, columnDefinition = "datetime default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt;

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

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getSubIndustry() {
        return subIndustry;
    }

    public void setSubIndustry(String subIndustry) {
        this.subIndustry = subIndustry;
    }

    public BigDecimal getMktCapital() {
        return mktCapital;
    }

    public void setMktCapital(BigDecimal mktCapital) {
        this.mktCapital = mktCapital;
    }

    public Integer getGfScore() {
        return gfScore;
    }

    public void setGfScore(Integer gfScore) {
        this.gfScore = gfScore;
    }

    public Integer getGfMomentum() {
        return gfMomentum;
    }

    public void setGfMomentum(Integer gfMomentum) {
        this.gfMomentum = gfMomentum;
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

    public BigDecimal getPrice52wLow() {
        return price52wLow;
    }

    public void setPrice52wLow(BigDecimal price52wLow) {
        this.price52wLow = price52wLow;
    }

    public BigDecimal getPrice52wHigh() {
        return price52wHigh;
    }

    public void setPrice52wHigh(BigDecimal price52wHigh) {
        this.price52wHigh = price52wHigh;
    }

    public BigDecimal getPrice3yLow() {
        return price3yLow;
    }

    public void setPrice3yLow(BigDecimal price3yLow) {
        this.price3yLow = price3yLow;
    }

    public BigDecimal getPrice3yHigh() {
        return price3yHigh;
    }

    public void setPrice3yHigh(BigDecimal price3yHigh) {
        this.price3yHigh = price3yHigh;
    }

    public BigDecimal getPrice5yLow() {
        return price5yLow;
    }

    public void setPrice5yLow(BigDecimal price5yLow) {
        this.price5yLow = price5yLow;
    }

    public BigDecimal getPrice5yHigh() {
        return price5yHigh;
    }

    public void setPrice5yHigh(BigDecimal price5yHigh) {
        this.price5yHigh = price5yHigh;
    }

    public BigDecimal getPrice10yHigh() {
        return price10yHigh;
    }

    public void setPrice10yHigh(BigDecimal price10yHigh) {
        this.price10yHigh = price10yHigh;
    }

    public BigDecimal getPrice10yLow() {
        return price10yLow;
    }

    public void setPrice10yLow(BigDecimal price10yLow) {
        this.price10yLow = price10yLow;
    }

    public BigDecimal getBeta() {
        return beta;
    }

    public void setBeta(BigDecimal beta) {
        this.beta = beta;
    }

    public BigDecimal getShillerPE() {
        return shillerPE;
    }

    public void setShillerPE(BigDecimal shillerPE) {
        this.shillerPE = shillerPE;
    }

    public BigDecimal getShillerPEMed() {
        return shillerPEMed;
    }

    public void setShillerPEMed(BigDecimal shillerPEMed) {
        this.shillerPEMed = shillerPEMed;
    }

    public BigDecimal getValuationAvg() {
        return valuationAvg;
    }

    public void setValuationAvg(BigDecimal valuationAvg) {
        this.valuationAvg = valuationAvg;
    }

    public BigDecimal getGfValue() {
        return gfValue;
    }

    public void setGfValue(BigDecimal gfValue) {
        this.gfValue = gfValue;
    }

    public Integer getGfScoreMed() {
        return gfScoreMed;
    }

    public void setGfScoreMed(Integer gfScoreMed) {
        this.gfScoreMed = gfScoreMed;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}