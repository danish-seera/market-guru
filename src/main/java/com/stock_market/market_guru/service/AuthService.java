package com.stock_market.market_guru.service;

import com.stock_market.market_guru.entity.RefreshToken;
import com.stock_market.market_guru.entity.User;
import com.stock_market.market_guru.repository.UserRepository;
import com.stock_market.market_guru.response.JwtResponse;
import com.stock_market.market_guru.security.JwtUtils;
import com.stock_market.market_guru.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private JwtUtils jwtUtils;
    
    @Autowired
    private RefreshTokenService refreshTokenService;

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public JwtResponse authenticate(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        User user = userPrincipal.getUser();
        
        String jwt = jwtUtils.generateToken(userPrincipal);
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user);
        
        return JwtResponse.builder()
                .token(jwt)
                .refreshToken(refreshToken.getToken())
                .user(JwtResponse.UserInfo.builder()
                        .id(user.getId())
                        .username(user.getUsername())
                        .name(user.getName())
                        .email(user.getEmail())
                        .role(user.getRole())
                        .build())
                .build();
    }
    
    public User signup(String username, String password, String name, String email) {
        // Check if username already exists
        if (userRepository.existsByUsername(username)) {
            throw new RuntimeException("Username is already taken");
        }
        
        // Check if email already exists
        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("Email is already in use");
        }
        
        // Create new user
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password)); // Encode password
        user.setName(name);
        user.setEmail(email);
        user.setRole("user"); // Default role is user
        user.setEnabled(true);
        
        return userRepository.save(user);
    }
    
    public JwtResponse refreshToken(String refreshToken) {
        return refreshTokenService.findByToken(refreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    UserPrincipal userPrincipal = new UserPrincipal(user);
                    String jwt = jwtUtils.generateToken(userPrincipal);
                    RefreshToken newRefreshToken = refreshTokenService.createRefreshToken(user);
                    
                    // Revoke old token
                    refreshTokenService.revokeToken(refreshToken);
                    
                    return JwtResponse.builder()
                            .token(jwt)
                            .refreshToken(newRefreshToken.getToken())
                            .user(JwtResponse.UserInfo.builder()
                                    .id(user.getId())
                                    .username(user.getUsername())
                                    .name(user.getName())
                                    .email(user.getEmail())
                                    .role(user.getRole())
                                    .build())
                            .build();
                })
                .orElseThrow(() -> new RuntimeException("Refresh token not found"));
    }
    
    public void logout(String refreshToken) {
        refreshTokenService.revokeToken(refreshToken);
    }
} 