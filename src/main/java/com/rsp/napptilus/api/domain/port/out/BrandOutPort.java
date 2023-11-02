package com.rsp.napptilus.api.domain.port.out;

import java.math.BigInteger;
import java.util.Optional;

import com.rsp.napptilus.api.domain.Brand;

public interface BrandOutPort {

	Optional<Brand> findById(BigInteger id);

}
