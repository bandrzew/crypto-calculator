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
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class CoinApiService {

	public static final String COIN_API_KEY_HEADER = "X-CoinAPI-Key";

	public static final String COIN_API_FILTER_DELIMITER = ";";

	@Value("${coin.api.key}")
	private String apiKey;

	@Value("${coin.api.url}")
	private String apiUrl;

	public Map<String, BigDecimal> getExchangeRates(String baseCurrency, List<String> exchangeCurrencies) {
		final UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(apiUrl)
				.path(baseCurrency)
				.queryParam("invert", false);

		if (exchangeCurrencies != null && !exchangeCurrencies.isEmpty())
			uriComponentsBuilder.queryParam("filter_asset_id", String.join(COIN_API_FILTER_DELIMITER, exchangeCurrencies));

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setInterceptors(singletonList(new HttpHeaderInterceptor(COIN_API_KEY_HEADER, apiKey)));
		ResponseEntity<CoinApiResponse> response = restTemplate.getForEntity(uriComponentsBuilder.encode().toUriString(),
				CoinApiResponse.class);

		return Optional.ofNullable(response.getBody())
				.map(CoinApiResponse::getRates)
				.orElseGet(Collections::emptyList)
				.stream()
				.collect(Collectors.toMap(CoinApiRateDto::getCurrency, CoinApiRateDto::getRate));
	}

}
