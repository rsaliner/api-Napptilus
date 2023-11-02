package com.rsp.napptilus.api.domain.port.in;

import java.math.BigInteger;
import java.time.LocalDateTime;

import com.rsp.napptilus.api.domain.Price;

public interface PriceInPort {

	Price obtainPrice(LocalDateTime applicationDate, BigInteger idProduct, BigInteger idBrand);
}
