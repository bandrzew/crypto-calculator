package com.crypto.calculator.currency.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyExchangeRequestDto implements Serializable {

	@Serial
	private static final long serialVersionUID = -4006467216228173015L;

	@NotBlank(message = "Base currency cannot be blank")
	private String from;

	@NotEmpty(message = "Exchange currency list cannot be empty")
	private List<String> to;

	@Min(value = 0, message = "Amount needs to be a positive number")
	private BigDecimal amount;

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public List<String> getTo() {
		return to;
	}

	public void setTo(List<String> to) {
		this.to = to;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

}
