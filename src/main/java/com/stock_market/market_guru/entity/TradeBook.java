package com.stock_market.market_guru.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "trade_book")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TradeBook {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trade_id")
    private Long tradeId;

    @Column(name = "user_id", nullable = false)
    private Integer userId;
    
    @Column(name = "broker", nullable = false)
    private String broker;
    
    @Column(name = "exchange", nullable = false)
    private String exchange;
    
    @Column(name = "instrument", nullable = false)
    private String instrument;
    
    @Column(name = "trade_date", nullable = false)
    private LocalDateTime tradeDate;
    
    @Column(name = "trade_type")
    private String tradeType;
    
    @Column(name = "quantity")
    private Integer quantity;
    
    @Column(name = "unit_price")
    private Integer unitPrice;
    
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
} 