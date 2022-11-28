package com.crypto.calculator;

import com.crypto.calculator.coin.api.service.CoinApiService;
import com.crypto.calculator.currency.controller.CurrencyController;
import com.crypto.calculator.currency.service.CurrencyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class CryptoCalculatorApplicationTests {

	private static final String MESSAGE = "Context failed to load";

	static {
		System.setProperty("COIN_API_KEY", "THIS-IS-SAMPLE-KEY");
	}

	@Autowired
	private CoinApiService coinApiService;

	@Autowired
	private CurrencyService currencyService;

	@Autowired
	private CurrencyController currencyController;

	@Test
	void contextLoads() {
		Assert.notNull(coinApiService, MESSAGE);
		Assert.notNull(currencyService, MESSAGE);
		Assert.notNull(currencyController, MESSAGE);
	}

}
