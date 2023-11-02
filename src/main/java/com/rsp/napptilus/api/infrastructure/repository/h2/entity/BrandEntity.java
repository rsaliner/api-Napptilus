package com.rsp.napptilus.api.infrastructure.repository.h2.entity;

import java.math.BigInteger;

import com.rsp.napptilus.api.domain.Brand;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@Table(name = "brands")
@NoArgsConstructor
@AllArgsConstructor
public class BrandEntity {

	@Id
	@GeneratedValue
	@Column(columnDefinition = "int8", name = "id", nullable = false, updatable = false, unique = true)
	private BigInteger id;

	@Column(columnDefinition = "varchar(150)", name = "name", nullable = false, updatable = true)
	private String name;

	public static BrandEntity fromDomain(Brand brand) {
		return BrandEntity.builder().id(brand.id()).name(brand.name()).build();
	}

	public Brand toDomain() {
		return new Brand(this.id, this.name);
	}

}
