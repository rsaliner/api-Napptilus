package com.rsp.napptilus.api.domain.port.out;

import java.math.BigInteger;
import java.util.Optional;

import com.rsp.napptilus.api.domain.Product;

public interface ProductOutPort {

	Optional<Product> findById(BigInteger id);

}
