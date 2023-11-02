package com.rsp.napptilus.api.infrastructure.repository.h2.entity;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

import com.rsp.napptilus.api.domain.Price;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@Table(name = "prices")
@NoArgsConstructor
@AllArgsConstructor
public class PriceEntity {

	@Id
	@GeneratedValue
	@Column(columnDefinition = "int8", name = "id", nullable = false, updatable = false, unique = true)
	private BigInteger id;

	@ManyToOne
	@JoinColumn(columnDefinition = "int8", name = "brand_id", nullable = false, updatable = false)
	private BrandEntity brand;

	@Column(columnDefinition = "timestamp", name = "start_date", nullable = false, updatable = true)
	private LocalDateTime startDate;

	@Column(columnDefinition = "timestamp", name = "end_date", nullable = false, updatable = true)
	private LocalDateTime endDate;

	@ManyToOne
	@JoinColumn(columnDefinition = "int8", name = "fee_id", nullable = false, updatable = false)
	private FeeEntity fee;

	@ManyToOne
	@JoinColumn(columnDefinition = "int8", name = "product_id", nullable = false, updatable = false)
	private ProductEntity product;

	@Column(columnDefinition = "int4", name = "priority", nullable = false, updatable = true)
	private int priority;

	@Column(columnDefinition = "NUMERIC(20, 2)", name = "amount", nullable = false, updatable = false)
	private BigDecimal amount;

	@Column(columnDefinition = "VARCHAR(10)", name = "curr", nullable = false, updatable = true)
	private String curr;

	public Price toDomain() {
		return new Price(this.id, this.brand.toDomain(), this.startDate, this.endDate, this.fee.toDomain(),
				this.product.toDomain(), this.priority, this.amount, this.curr);
	}

	public static PriceEntity fromDomain(Price price) {
		return PriceEntity.builder().id(price.id()).brand(BrandEntity.fromDomain(price.brand()))
				.startDate(price.startDate()).endDate(price.endDate()).fee(FeeEntity.fromDomain(price.fee()))
				.product(ProductEntity.fromDomain(price.product())).priority(price.priority()).amount(price.amount())
				.curr(price.curr()).build();
	}

}
