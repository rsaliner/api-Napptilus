package com.rsp.napptilus.api.domain.port.out;

import java.time.LocalDateTime;
import java.util.Optional;

import com.rsp.napptilus.api.domain.Brand;
import com.rsp.napptilus.api.domain.Price;
import com.rsp.napptilus.api.domain.Product;

public interface PriceOutPort {

	Optional<Price> findPrice(LocalDateTime applicationDate, Product product, Brand brand);

}
