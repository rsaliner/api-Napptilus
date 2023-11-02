package com.rsp.napptilus.api.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rsp.napptilus.api.domain.port.in.PriceInPort;
import com.rsp.napptilus.api.domain.port.out.BrandOutPort;
import com.rsp.napptilus.api.domain.port.out.PriceOutPort;
import com.rsp.napptilus.api.domain.port.out.ProductOutPort;
import com.rsp.napptilus.api.domain.service.PriceInService;
import com.rsp.napptilus.api.infrastructure.repository.h2.adapter.BrandAdapter;
import com.rsp.napptilus.api.infrastructure.repository.h2.adapter.PriceAdapter;
import com.rsp.napptilus.api.infrastructure.repository.h2.adapter.ProductAdapter;

@Configuration
public class BeanConfiguration {

	@Bean
	PriceOutPort priceOutPort() {
		return new PriceAdapter();
	}

	@Bean
	BrandOutPort brandOutPort() {
		return new BrandAdapter();
	}

	@Bean
	ProductOutPort productOutPort() {
		return new ProductAdapter();
	}

	@Bean
	PriceInPort priceInPort() {
		return new PriceInService(priceOutPort(), brandOutPort(), productOutPort());
	}

}
