package com.crypto.calculator;

import com.crypto.calculator.coin.api.service.CoinApiService;
import com.crypto.calculator.currency.controller.CurrencyController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CryptoCalculatorApplicationTests {

	@Autowired
	private CoinApiService coinApiService;

	@Autowired
	private CurrencyController currencyController;

	@Test
	void contextLoads() {
		Assertions.assertThat(coinApiService).isNotNull();
		Assertions.assertThat(currencyController).isNotNull();
	}

}
