package com.stock_market.market_guru.controller;

import com.stock_market.market_guru.entity.User;
import com.stock_market.market_guru.request.LoginRequest;
import com.stock_market.market_guru.request.RefreshTokenRequest;
import com.stock_market.market_guru.request.SignupRequest;
import com.stock_market.market_guru.response.ApiResponse;
import com.stock_market.market_guru.response.JwtResponse;
import com.stock_market.market_guru.security.UserPrincipal;
import com.stock_market.market_guru.service.AuthService;
import com.stock_market.market_guru.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {
    
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    
    @Autowired
    private AuthService authService;
    
    @Autowired
    private UserService userService;
    
    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<JwtResponse>> signup(@RequestBody SignupRequest signupRequest) {
        logger.info("Signup request received for user: {}", signupRequest.getUsername());
        try {
            // Create the user
            User user = authService.signup(
                    signupRequest.getUsername(), 
                    signupRequest.getPassword(),
                    signupRequest.getName(),
                    signupRequest.getEmail());
            
            // Authenticate the user after signup
            JwtResponse jwtResponse = authService.authenticate(
                    signupRequest.getUsername(), 
                    signupRequest.getPassword());
            
            logger.info("User registered and logged in successfully: {}", signupRequest.getUsername());
            return ResponseEntity.ok(ApiResponse.success(jwtResponse, "User registered successfully"));
        } catch (Exception e) {
            logger.error("Signup failed for user {}: {}", signupRequest.getUsername(), e.getMessage());
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }
    
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<JwtResponse>> login(@RequestBody LoginRequest loginRequest) {
        logger.info("Login request received for user: {}", loginRequest.getUsername());
        try {
            JwtResponse jwtResponse = authService.authenticate(
                    loginRequest.getUsername(), loginRequest.getPassword());
            
            logger.info("User logged in successfully: {}", loginRequest.getUsername());
            return ResponseEntity.ok(ApiResponse.success(jwtResponse, "Login successful"));
        } catch (Exception e) {
            logger.error("Login failed for user {}: {}", loginRequest.getUsername(), e.getMessage());
            return ResponseEntity.status(401).body(ApiResponse.error("Invalid username or password"));
        }
    }
    
    @GetMapping("/me")
    public ResponseEntity<ApiResponse<JwtResponse.UserInfo>> getUserInfo(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        logger.info("Fetching user info for: {}", userPrincipal.getUsername());
        try {
            User user = userPrincipal.getUser();
            
            JwtResponse.UserInfo userInfo = JwtResponse.UserInfo.builder()
                    .id(user.getId())
                    .username(user.getUsername())
                    .name(user.getName())
                    .email(user.getEmail())
                    .role(user.getRole())
                    .build();
            
            return ResponseEntity.ok(ApiResponse.success(userInfo));
        } catch (Exception e) {
            logger.error("Error fetching user info: {}", e.getMessage());
            return ResponseEntity.status(401).body(ApiResponse.error("Unauthorized access"));
        }
    }
    
    @PostMapping("/refresh")
    public ResponseEntity<ApiResponse<JwtResponse>> refreshToken(@RequestBody RefreshTokenRequest request) {
        logger.info("Token refresh request received");
        try {
            JwtResponse jwtResponse = authService.refreshToken(request.getRefreshToken());
            logger.info("Token refreshed successfully");
            return ResponseEntity.ok(ApiResponse.success(jwtResponse));
        } catch (Exception e) {
            logger.error("Token refresh failed: {}", e.getMessage());
            return ResponseEntity.status(401).body(ApiResponse.error("Invalid refresh token"));
        }
    }
    
    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<Void>> logout(@RequestBody RefreshTokenRequest request) {
        logger.info("Logout request received");
        try {
            authService.logout(request.getRefreshToken());
            logger.info("User logged out successfully");
            return ResponseEntity.ok(ApiResponse.success(null, "Logged out successfully"));
        } catch (Exception e) {
            logger.error("Logout failed: {}", e.getMessage());
            return ResponseEntity.badRequest().body(ApiResponse.error("Logout failed"));
        }
    }
} 