package com.rsp.napptilus.api.application.rest.response;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

import com.rsp.napptilus.api.domain.Price;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetPriceResponse {
	private BigInteger idProduct;
	private BigInteger idBrand;
	private BigInteger idFee;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private BigDecimal amount;
	private String curr;

	public static GetPriceResponse fromDomain(Price price) {
		return GetPriceResponse.builder().idProduct(price.product().id()).idBrand(price.brand().id())
				.idFee(price.fee().id()).startDate(price.startDate()).endDate(price.endDate()).amount(price.amount())
				.curr(price.curr()).build();
	}
}
