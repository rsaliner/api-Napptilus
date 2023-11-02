package com.rsp.napptilus.api.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.rsp.napptilus.api.domain.Brand;
import com.rsp.napptilus.api.domain.Fee;
import com.rsp.napptilus.api.domain.Price;
import com.rsp.napptilus.api.domain.Product;
import com.rsp.napptilus.api.domain.error.DomainException;
import com.rsp.napptilus.api.domain.port.out.BrandOutPort;
import com.rsp.napptilus.api.domain.port.out.PriceOutPort;
import com.rsp.napptilus.api.domain.port.out.ProductOutPort;
import com.rsp.napptilus.api.domain.service.PriceInService;

class PriceInServiceTest {

	private PriceOutPort priceOutPort;
	private BrandOutPort brandOutPort;
	private ProductOutPort productOutPort;
	private PriceInService priceInService;

	@BeforeEach
	void setUp() {
		priceOutPort = mock(PriceOutPort.class);
		brandOutPort = mock(BrandOutPort.class);
		productOutPort = mock(ProductOutPort.class);
		priceInService = new PriceInService(priceOutPort, brandOutPort, productOutPort);
	}

	@Test
	void shouldNotObtainPrice_whenBrandNotExist_thenTrhowException() {
		BigInteger idBrand = BigInteger.TWO;
		BigInteger idProduct = BigInteger.TWO;
		LocalDateTime date = LocalDateTime.now();

		when(brandOutPort.findById(idBrand)).thenReturn(Optional.empty());

		assertThrows(DomainException.class, () -> {
			priceInService.obtainPrice(date, idProduct, idBrand);
		});

	}

	@Test
	void shouldNotObtainPrice_whenProductNotExist_thenTrhowException() {
		BigInteger idBrand = BigInteger.TWO;
		BigInteger idProduct = BigInteger.TWO;
		LocalDateTime date = LocalDateTime.now();

		when(productOutPort.findById(idProduct)).thenReturn(Optional.empty());

		assertThrows(DomainException.class, () -> {
			priceInService.obtainPrice(date, idProduct, idBrand);
		});

	}

	@Test
	void shouldObtainPrice_whenBrandAndProductExist() {
		Fee fee = new Fee(BigInteger.ONE, "Fee Test");
		Brand brand = new Brand(BigInteger.ONE, "Brand Test");
		Product product = new Product(BigInteger.ONE, "Product Test");
		LocalDateTime date = LocalDateTime.now();

		when(brandOutPort.findById(brand.id())).thenReturn(Optional.of(brand));
		when(productOutPort.findById(product.id())).thenReturn(Optional.of(product));
		when(priceOutPort.findPrice(date, product, brand)).thenReturn(Optional.of(new Price(BigInteger.ONE, brand,
				LocalDateTime.MIN, LocalDateTime.MAX, fee, product, 0, BigDecimal.TEN, "EUR")));

		Price priceObtained = priceInService.obtainPrice(date, product.id(), brand.id());

		assertEquals(BigInteger.ONE, priceObtained.product().id());
		assertEquals(BigInteger.ONE, priceObtained.brand().id());
		assertEquals(LocalDateTime.MAX, priceObtained.endDate());

	}

}
