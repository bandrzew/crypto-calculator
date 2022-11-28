package com.crypto.calculator.currency.controller;

import com.crypto.calculator.coin.api.service.CoinApiService;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
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
	public Map<String, BigDecimal> getRates(@PathVariable("currency") String currency, @RequestParam("filter[]") List<String> filters) {
		return coinApiService.getExchangeRates(currency, filters);
	}

}
