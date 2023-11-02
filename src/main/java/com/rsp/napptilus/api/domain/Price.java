package com.rsp.napptilus.api.domain;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

public record Price(BigInteger id, Brand brand, LocalDateTime startDate, LocalDateTime endDate, Fee fee,
		Product product, int priority, BigDecimal amount, String curr) {
}
