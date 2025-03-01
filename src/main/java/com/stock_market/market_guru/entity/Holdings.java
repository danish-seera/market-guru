package com.stock_market.market_guru.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "holdings")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Holdings {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Integer userId;
    
    @Column(name = "broker", nullable = false)
    private String broker;
    
    @Column(name = "exchange", nullable = false)
    private String exchange;
    
    @Column(name = "instrument", nullable = false)
    private String instrument;
    
    @Column(name = "quantity", nullable = false)
    private Integer quantity;
    
    @Column(name = "avg_price", nullable = false)
    private Integer avgPrice;


} 