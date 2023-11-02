package com.rsp.napptilus.api.infrastructure.repository.h2.adapter;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.rsp.napptilus.api.domain.Brand;
import com.rsp.napptilus.api.domain.Price;
import com.rsp.napptilus.api.domain.Product;
import com.rsp.napptilus.api.domain.port.out.PriceOutPort;
import com.rsp.napptilus.api.infrastructure.repository.h2.SpringDatah2PriceRepository;
import com.rsp.napptilus.api.infrastructure.repository.h2.entity.BrandEntity;
import com.rsp.napptilus.api.infrastructure.repository.h2.entity.PriceEntity;
import com.rsp.napptilus.api.infrastructure.repository.h2.entity.ProductEntity;

public class PriceAdapter implements PriceOutPort {

	@Autowired
	private SpringDatah2PriceRepository priceRepository;

	@Override
	public Optional<Price> findPrice(LocalDateTime applicationDate, Product product, Brand brand) {
		return priceRepository
				.findFirstByAndSort(applicationDate, ProductEntity.fromDomain(product), BrandEntity.fromDomain(brand))
				.map(PriceEntity::toDomain);
	}

}
