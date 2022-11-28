package com.crypto.calculator.currency.controller;

import com.crypto.calculator.coin.api.service.CoinApiService;
import com.crypto.calculator.currency.dto.CurrencyRatesDto;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "currencies")
public class CurrencyController {

	private final CoinApiService coinApiService;

	public CurrencyController(CoinApiService coinApiService) {
		this.coinApiService = coinApiService;
	}

	@GetMapping("{currency}")
	public CurrencyRatesDto getRates(@PathVariable("currency") String currency,
			@RequestParam(value = "filter[]", required = false) List<String> filters) {
		return new CurrencyRatesDto(currency, coinApiService.getExchangeRates(currency, filters));
	}

}
