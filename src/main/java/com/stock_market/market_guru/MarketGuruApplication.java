package com.stock_market.market_guru;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableFeignClients
@EnableScheduling // Enable annotation-based scheduling
public class MarketGuruApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarketGuruApplication.class, args);
	}

}
