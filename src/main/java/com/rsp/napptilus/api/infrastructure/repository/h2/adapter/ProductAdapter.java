package com.rsp.napptilus.api.infrastructure.repository.h2.adapter;

import java.math.BigInteger;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.rsp.napptilus.api.domain.Product;
import com.rsp.napptilus.api.domain.port.out.ProductOutPort;
import com.rsp.napptilus.api.infrastructure.repository.h2.SpringDatah2ProductRepository;
import com.rsp.napptilus.api.infrastructure.repository.h2.entity.ProductEntity;

public class ProductAdapter implements ProductOutPort {

	@Autowired
	private SpringDatah2ProductRepository productRepository;

	@Override
	public Optional<Product> findById(BigInteger id) {
		return productRepository.findById(id).map(ProductEntity::toDomain);
	}

}
