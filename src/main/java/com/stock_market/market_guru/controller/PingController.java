package com.stock_market.market_guru.controller;

import com.stock_market.market_guru.response.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/ping")
public class PingController {
    
    private static final Logger logger = LoggerFactory.getLogger(PingController.class);
    
    @GetMapping
    public ResponseEntity<ApiResponse<Map<String, Object>>> ping() {
        logger.info("Ping request received");
        
        Map<String, Object> response = new HashMap<>();
        response.put("status", "UP");
        response.put("timestamp", LocalDateTime.now().toString());
        response.put("message", "Server is running");
        
        return ResponseEntity.ok(ApiResponse.success(response, "Server is up and running"));
    }
} 