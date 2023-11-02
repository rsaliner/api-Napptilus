package com.rsp.napptilus.api.infrastructure.repository.h2.adapter;

import java.math.BigInteger;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.rsp.napptilus.api.domain.Brand;
import com.rsp.napptilus.api.domain.port.out.BrandOutPort;
import com.rsp.napptilus.api.infrastructure.repository.h2.SpringDatah2BrandRepository;
import com.rsp.napptilus.api.infrastructure.repository.h2.entity.BrandEntity;

public class BrandAdapter implements BrandOutPort {

	@Autowired
	private SpringDatah2BrandRepository brandRepository;

	@Override
	public Optional<Brand> findById(BigInteger id) {
		return brandRepository.findById(id).map(BrandEntity::toDomain);
	}

}
