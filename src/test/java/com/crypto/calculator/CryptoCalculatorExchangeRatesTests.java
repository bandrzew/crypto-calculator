package com.crypto.calculator;

import static org.mockito.Mockito.mock;

import com.crypto.calculator.coin.api.service.CoinApiService;
import java.math.BigDecimal;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class CryptoCalculatorExchangeRatesTests {

	static {
		System.setProperty("COIN_API_KEY", "THIS-IS-SAMPLE-KEY");
	}

	private final CoinApiService coinApiService = mock(CoinApiService.class,
			invocation -> Map.of("ETH", new BigDecimal(13), "USDT", new BigDecimal(16000)));

	@Test
	void calculation() {
		Assert.notEmpty(coinApiService.getExchangeRates("BTC", null), "Failed to obtain exchange rates");
	}

}
