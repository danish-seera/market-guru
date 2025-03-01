package com.stock_market.market_guru.repository;

import com.stock_market.market_guru.entity.RefreshToken;
import com.stock_market.market_guru.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);
    
    @Modifying
    void deleteByUser(User user);
    
    Optional<RefreshToken> findByUserAndRevokedFalse(User user);
} 