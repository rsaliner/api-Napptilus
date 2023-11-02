package com.rsp.napptilus.api.domain.service;

import java.math.BigInteger;
import java.time.LocalDateTime;

import com.rsp.napptilus.api.domain.Brand;
import com.rsp.napptilus.api.domain.Price;
import com.rsp.napptilus.api.domain.Product;
import com.rsp.napptilus.api.domain.error.DomainException;
import com.rsp.napptilus.api.domain.port.in.PriceInPort;
import com.rsp.napptilus.api.domain.port.out.BrandOutPort;
import com.rsp.napptilus.api.domain.port.out.PriceOutPort;
import com.rsp.napptilus.api.domain.port.out.ProductOutPort;

public class PriceInService implements PriceInPort {

	private PriceOutPort priceOutPort;
	private BrandOutPort brandOutPort;
	private ProductOutPort productOutPort;

	public PriceInService(PriceOutPort priceOutPort, BrandOutPort brandOutPort, ProductOutPort productOutPort) {
		this.priceOutPort = priceOutPort;
		this.brandOutPort = brandOutPort;
		this.productOutPort = productOutPort;
	}

	@Override
	public Price obtainPrice(LocalDateTime applicationDate, BigInteger idProduct, BigInteger idBrand) {

		Brand brand = brandOutPort.findById(idBrand)
				.orElseThrow(() -> new DomainException("Informed brand (" + idBrand + ") does not exists"));

		Product product = productOutPort.findById(idProduct)
				.orElseThrow(() -> new DomainException("Informed product (" + idProduct + ") does not exists"));

		return priceOutPort.findPrice(applicationDate, product, brand)
				.orElseThrow(() -> new DomainException("There is no price registered for the input data"));
	}
}
