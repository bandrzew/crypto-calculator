package com.crypto.calculator.coin.api.service;

import static java.util.Collections.singletonList;

import com.crypto.calculator.coin.api.dto.CoinApiRateDto;
import com.crypto.calculator.coin.api.dto.CoinApiResponse;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.devtools.remote.client.HttpHeaderInterceptor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CoinApiService {

	public static final String COIN_API_KEY_HEADER = "X-CoinAPI-Key";

	public static final String COIN_API_PATH = "https://rest.coinapi.io/v1/exchangerate/{baseCurrency}?invert=false&filter_asset_id={exchangeCurrencies}";

	public static final String COIN_API_FILTER_DELIMITER = ";";

	@Value("${coin.api.key}")
	private String apiKey;

	public Map<String, BigDecimal> getExchangeRates(String baseCurrency, List<String> exchangeCurrencies) {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setInterceptors(singletonList(new HttpHeaderInterceptor(COIN_API_KEY_HEADER, apiKey)));
		ResponseEntity<CoinApiResponse> response = restTemplate.getForEntity(COIN_API_PATH, CoinApiResponse.class, baseCurrency,
				String.join(COIN_API_FILTER_DELIMITER, exchangeCurrencies));

		return Optional.ofNullable(response.getBody())
				.map(CoinApiResponse::getRates)
				.orElseGet(Collections::emptyList)
				.stream()
				.collect(Collectors.toMap(CoinApiRateDto::getCurrency, CoinApiRateDto::getRate));
	}

}
