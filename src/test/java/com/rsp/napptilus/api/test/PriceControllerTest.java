package com.rsp.napptilus.api.test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;

import com.rsp.napptilus.api.application.rest.PriceController;
import com.rsp.napptilus.api.domain.Brand;
import com.rsp.napptilus.api.domain.Fee;
import com.rsp.napptilus.api.domain.Price;
import com.rsp.napptilus.api.domain.Product;
import com.rsp.napptilus.api.domain.error.DomainException;
import com.rsp.napptilus.api.domain.service.PriceInService;

@WebMvcTest(PriceController.class)
class PriceControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PriceInService priceInService;

	@Test
	void obtainPriceByDateAndProductAndBrandEndpoint() throws Exception {

		LocalDateTime dateTime = LocalDateTime.parse("2020-06-14T10:00:00");
		BigInteger idProduct = BigInteger.ONE;
		BigInteger idBrand = BigInteger.ONE;

		when(priceInService.obtainPrice(dateTime, idProduct, idBrand)).thenReturn(new Price(BigInteger.ONE,
				new Brand(BigInteger.ONE, "brand"), LocalDateTime.parse("2020-06-14T00:00:00"),
				LocalDateTime.parse("2020-12-31T23:59:59"), new Fee(BigInteger.ONE, "fee"),
				new Product(BigInteger.ONE, "Product 1"), 0, new BigDecimal("35.50"), "EUR"));

		mockMvc.perform(get("/prices").param("date", dateTime.toString()).param("idProduct", idProduct.toString())
				.param("idBrand", idBrand.toString()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.idProduct").value(1)).andExpect(jsonPath("$.idBrand").value(1))
				.andExpect(jsonPath("$.amount").value(35.50));

	}

	@Test
	void obtainPriceByDateAndProductAndBrandEndpoint_shouldDomainException() throws Exception {

		LocalDateTime dateTime = LocalDateTime.parse("2020-06-14T10:00:00");
		BigInteger idProduct = BigInteger.ONE;
		BigInteger idBrand = BigInteger.ONE;

		when(priceInService.obtainPrice(dateTime, idProduct, idBrand))
				.thenThrow(new DomainException("Informed product (" + idProduct + ") does not exists"));

		mockMvc.perform(get("/prices").param("date", dateTime.toString()).param("idProduct", idProduct.toString())
				.param("idBrand", idBrand.toString()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(result -> assertTrue(result.getResolvedException() instanceof ResponseStatusException));

	}

}
