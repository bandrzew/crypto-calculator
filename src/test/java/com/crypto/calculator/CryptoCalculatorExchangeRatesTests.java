package com.crypto.calculator;

import static org.mockito.Mockito.mock;

import com.crypto.calculator.coin.api.service.CoinApiService;
import com.crypto.calculator.currency.dto.CurrencyExchangeDetailDto;
import com.crypto.calculator.currency.dto.CurrencyExchangeRequestDto;
import com.crypto.calculator.currency.service.CurrencyService;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.util.Assert;

@SpringBootTest
class CryptoCalculatorExchangeRatesTests {

	static {
		System.setProperty("COIN_API_KEY", "THIS-IS-SAMPLE-KEY");
	}

	private final CoinApiService coinApiService = mock(CoinApiService.class,
			invocation -> Map.of("ETH", new BigDecimal(10), "USDT", new BigDecimal(100)));

	private final CurrencyService currencyService = new CurrencyService(coinApiService);

	@Test
	void calculation() {
		ReflectionTestUtils.setField(currencyService, "exchangeFee", new BigDecimal("0.01"));
		CurrencyExchangeRequestDto currencyExchangeRequestDto = new CurrencyExchangeRequestDto();
		currencyExchangeRequestDto.setFrom("BTC");
		currencyExchangeRequestDto.setTo(List.of("ETH", "USDT"));
		currencyExchangeRequestDto.setAmount(new BigDecimal(100));

		final Map<String, Object> result = currencyService.exchangeCurrency(currencyExchangeRequestDto);
		Assert.state("BTC".equals(result.get("from")), "From needs to be present in response");
		Assert.isInstanceOf(CurrencyExchangeDetailDto.class, result.get("ETH"), "Calculation needs to be CurrencyExchangeDetailDto object");
		Assert.isInstanceOf(CurrencyExchangeDetailDto.class, result.get("USDT"),
				"Calculation needs to be CurrencyExchangeDetailDto object");

		final CurrencyExchangeDetailDto detailDto = (CurrencyExchangeDetailDto) result.get("ETH");
		Assert.state(new BigDecimal(100).equals(detailDto.getAmount()), "Amount has to stay unchanged");
		Assert.state(new BigDecimal(10).equals(detailDto.getRate()), "Rate needs to be populated by coinApiService");
		Assert.state(new BigDecimal(1).compareTo(detailDto.getFee()) == 0, "Fee needs to be calculated from amount");
		Assert.state(new BigDecimal(990).compareTo(detailDto.getResult()) == 0, "Result needs to be calculated from amount lowered by fee");
	}

}
