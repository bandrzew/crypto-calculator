package com.crypto.calculator.currency.dto;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

public class CurrencyRatesDto implements Serializable {

	@Serial
	private static final long serialVersionUID = -3352838542153928805L;

	private String source;

	private Map<String, BigDecimal> rates;

	public CurrencyRatesDto() {
	}

	public CurrencyRatesDto(String source, Map<String, BigDecimal> rates) {
		this.source = source;
		this.rates = rates;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Map<String, BigDecimal> getRates() {
		return rates;
	}

	public void setRates(Map<String, BigDecimal> rates) {
		this.rates = rates;
	}

}
