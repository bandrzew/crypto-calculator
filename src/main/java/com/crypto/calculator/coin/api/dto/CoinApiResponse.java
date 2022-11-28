package com.crypto.calculator.coin.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CoinApiResponse implements Serializable {

	@Serial
	private static final long serialVersionUID = 2797998906565446883L;

	@JsonProperty("asset_id_base")
	private String baseCurrency;

	private List<CoinApiRateDto> rates;

	public String getBaseCurrency() {
		return baseCurrency;
	}

	public void setBaseCurrency(String baseCurrency) {
		this.baseCurrency = baseCurrency;
	}

	public List<CoinApiRateDto> getRates() {
		return rates;
	}

	public void setRates(List<CoinApiRateDto> rates) {
		this.rates = rates;
	}

}
