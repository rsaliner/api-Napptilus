package com.rsp.napptilus.api.application.rest.request;

import java.math.BigInteger;
import java.time.LocalDateTime;

public record GetPriceRequest(LocalDateTime date, BigInteger idProduct, BigInteger idBrand) {

}
