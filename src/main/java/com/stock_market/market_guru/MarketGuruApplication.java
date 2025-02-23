package com.stock_market.market_guru;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MarketGuruApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarketGuruApplication.class, args);
	}

}
