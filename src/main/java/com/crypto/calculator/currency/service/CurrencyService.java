package com.crypto.calculator.currency.service;

import com.crypto.calculator.coin.api.service.CoinApiService;
import com.crypto.calculator.currency.dto.CurrencyExchangeDetailDto;
import com.crypto.calculator.currency.dto.CurrencyExchangeRequestDto;
import com.crypto.calculator.currency.dto.CurrencyRatesDto;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CurrencyService {

	@Value("${currency.exchange.fee}")
	private BigDecimal exchangeFee;

	private final CoinApiService coinApiService;

	public CurrencyService(CoinApiService coinApiService) {
		this.coinApiService = coinApiService;
	}

	public CurrencyRatesDto getCurrencyRates(String currency, List<String> filters) {
		return new CurrencyRatesDto(currency, coinApiService.getExchangeRates(currency, filters));
	}

	public Map<String, Object> exchangeCurrency(CurrencyExchangeRequestDto currencyExchangeRequestDto) {
		final Map<String, BigDecimal> exchangeRates = coinApiService.getExchangeRates(currencyExchangeRequestDto.getFrom(),
				currencyExchangeRequestDto.getTo());

		Map<String, Object> result = new LinkedHashMap<>();
		result.put("from", currencyExchangeRequestDto.getFrom());

		for (String currency : currencyExchangeRequestDto.getTo()) {
			result.put(currency, calculate(exchangeRates, currency, currencyExchangeRequestDto.getAmount()));
		}

		return result;
	}

	private CurrencyExchangeDetailDto calculate(Map<String, BigDecimal> exchangeRates, String currency, BigDecimal amount) {
		CurrencyExchangeDetailDto currencyExchangeDetail = new CurrencyExchangeDetailDto();
		final BigDecimal rate = exchangeRates.get(currency);
		final BigDecimal fee = amount.multiply(exchangeFee);

		currencyExchangeDetail.setAmount(amount);
		currencyExchangeDetail.setRate(rate);
		currencyExchangeDetail.setFee(fee);
		currencyExchangeDetail.setResult(amount.subtract(fee).multiply(rate));

		return currencyExchangeDetail;
	}

}
