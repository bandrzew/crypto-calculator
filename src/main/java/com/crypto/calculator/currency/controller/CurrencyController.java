package com.crypto.calculator.currency.controller;

import com.crypto.calculator.currency.dto.CurrencyExchangeRequestDto;
import com.crypto.calculator.currency.dto.CurrencyExchangeDetailDto;
import com.crypto.calculator.currency.dto.CurrencyRatesDto;
import com.crypto.calculator.currency.service.CurrencyService;
import java.util.List;
import java.util.Map;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "currencies")
public class CurrencyController {

	private final CurrencyService currencyService;

	public CurrencyController(CurrencyService currencyService) {
		this.currencyService = currencyService;
	}

	@GetMapping("{currency}")
	public CurrencyRatesDto getRates(@PathVariable("currency") String currency,
			@RequestParam(value = "filter[]", required = false) List<String> filters) {
		return currencyService.getCurrencyRates(currency, filters);
	}

	@PostMapping("exchange")
	public Map<String, Object> exchangeCurrency(@Validated @RequestBody CurrencyExchangeRequestDto currencyExchangeRequestDto) {
		return currencyService.exchangeCurrency(currencyExchangeRequestDto);
	}

}
