package com.stock_market.market_guru.entity;

import lombok.Data;

import jakarta.persistence.*;

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
    private Double mktCapital;

    @Column(name = "gf_score")
    private Integer gfScore;

    @Column(name = "gf_momentum")
    private Integer gfMomentum;

    @Column(name = "gf_risk_assessment", length = 255)
    private String gfRiskAssessment;

    @Column(name = "gf_remark", length = 50)
    private String gfRemark;

    @Column(name = "price52wlow", precision = 15, scale = 4)
    private Double price52wLow;

    @Column(name = "price52whigh", precision = 15, scale = 4)
    private Double price52wHigh;

    @Column(name = "price3ylow", precision = 15, scale = 4)
    private Double price3yLow;

    @Column(name = "price3yhigh", precision = 15, scale = 4)
    private Double price3yHigh;

    @Column(name = "price5ylow", precision = 15, scale = 4)
    private Double price5yLow;

    @Column(name = "price5yhigh", precision = 15, scale = 4)
    private Double price5yHigh;

    @Column(name = "price10yhigh", precision = 15, scale = 4)
    private Double price10yHigh;

    @Column(name = "price10ylow", precision = 15, scale = 4)
    private Double price10yLow;

    @Column(name = "beta", precision = 10, scale = 4)
    private Double beta;

    @Column(name = "ShillerPE", precision = 15, scale = 4)
    private Double shillerPE;

    @Column(name = "ShillerPE_med", precision = 15, scale = 4)
    private Double shillerPEMed;

    @Column(name = "valuation_avg", precision = 15, scale = 4)
    private Double valuationAvg;

    @Column(name = "gf_value", precision = 15, scale = 4)
    private Double gfValue;

    @Column(name = "gf_score_med")
    private Integer gfScoreMed;

    @Column(name = "created_at", nullable = false, columnDefinition = "datetime default CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false, columnDefinition = "datetime default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt;
}