package com.rsp.napptilus.api.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.rsp.napptilus.api.domain.Price;
import com.rsp.napptilus.api.domain.service.PriceInService;

@SpringBootTest
class IntegrationTests {

	@Autowired
	private PriceInService priceInService;

	private static BigInteger idProduct;
	private static BigInteger idBrand;

	@BeforeAll
	public static void setUp() {
		idProduct = BigInteger.valueOf(35455);
		idBrand = BigInteger.valueOf(1);
	}

	@Test
	void contextLoads() {
		assertThat(priceInService).isNotNull();
	}

	@Test
	void givenDataBaseLoaded_IntegrationTest1() {
		LocalDateTime ldt = LocalDateTime.of(2020, 6, 14, 10, 0, 0);

		Price price = priceInService.obtainPrice(ldt, idProduct, idBrand);

		assertEquals(LocalDateTime.of(2020, 6, 14, 0, 0, 0), price.startDate());
		assertEquals(LocalDateTime.of(2020, 12, 31, 23, 59, 59), price.endDate());
		assertEquals(BigInteger.ONE, price.fee().id());
		assertEquals(BigInteger.valueOf(35455), price.product().id());
		assertEquals(0, price.priority());
		assertEquals(0, BigDecimal.valueOf(35.50).compareTo(price.amount()));
		assertEquals("EUR", price.curr());
	}

	@Test
	void givenDataBaseLoaded_IntegrationTest2() {
		LocalDateTime ldt = LocalDateTime.of(2020, 6, 14, 16, 0, 0);

		Price price = priceInService.obtainPrice(ldt, idProduct, idBrand);

		assertEquals(LocalDateTime.of(2020, 6, 14, 15, 0, 0), price.startDate());
		assertEquals(LocalDateTime.of(2020, 6, 14, 18, 30, 0), price.endDate());
		assertEquals(BigInteger.TWO, price.fee().id());
		assertEquals(BigInteger.valueOf(35455), price.product().id());
		assertEquals(1, price.priority());
		assertEquals(0, BigDecimal.valueOf(25.45).compareTo(price.amount()));
		assertEquals("EUR", price.curr());
	}

	@Test
	void givenDataBaseLoaded_IntegrationTest3() {
		LocalDateTime ldt = LocalDateTime.of(2020, 6, 14, 21, 0, 0);

		Price price = priceInService.obtainPrice(ldt, idProduct, idBrand);

		assertEquals(LocalDateTime.of(2020, 6, 14, 0, 0, 0), price.startDate());
		assertEquals(LocalDateTime.of(2020, 12, 31, 23, 59, 59), price.endDate());
		assertEquals(BigInteger.ONE, price.fee().id());
		assertEquals(BigInteger.valueOf(35455), price.product().id());
		assertEquals(0, price.priority());
		assertEquals(0, BigDecimal.valueOf(35.50).compareTo(price.amount()));
		assertEquals("EUR", price.curr());
	}

	@Test
	void givenDataBaseLoaded_IntegrationTest4() {
		LocalDateTime ldt = LocalDateTime.of(2020, 6, 15, 10, 0, 0);

		Price price = priceInService.obtainPrice(ldt, idProduct, idBrand);

		assertEquals(LocalDateTime.of(2020, 6, 15, 00, 0, 0), price.startDate());
		assertEquals(LocalDateTime.of(2020, 6, 15, 11, 0, 0), price.endDate());
		assertEquals(BigInteger.valueOf(3), price.fee().id());
		assertEquals(BigInteger.valueOf(35455), price.product().id());
		assertEquals(1, price.priority());
		assertEquals(0, BigDecimal.valueOf(30.50).compareTo(price.amount()));
		assertEquals("EUR", price.curr());
	}

	@Test
	void givenDataBaseLoaded_IntegrationTest5() {
		LocalDateTime ldt = LocalDateTime.of(2020, 6, 16, 21, 0, 0);

		Price price = priceInService.obtainPrice(ldt, idProduct, idBrand);

		assertEquals(LocalDateTime.of(2020, 6, 15, 16, 0, 0), price.startDate());
		assertEquals(LocalDateTime.of(2020, 12, 31, 23, 59, 59), price.endDate());
		assertEquals(BigInteger.valueOf(4), price.fee().id());
		assertEquals(BigInteger.valueOf(35455), price.product().id());
		assertEquals(1, price.priority());
		assertEquals(0, BigDecimal.valueOf(38.95).compareTo(price.amount()));
		assertEquals("EUR", price.curr());
	}

}
