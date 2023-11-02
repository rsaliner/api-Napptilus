package com.rsp.napptilus.api.infrastructure.repository.h2.entity;

import java.math.BigInteger;

import com.rsp.napptilus.api.domain.Product;

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
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {

	@Id
	@GeneratedValue
	@Column(columnDefinition = "int8", name = "id", nullable = false, updatable = false, unique = true)
	private BigInteger id;

	@Column(columnDefinition = "varchar(150)", name = "name", nullable = false, updatable = true)
	private String name;

	public static ProductEntity fromDomain(Product product) {
		return ProductEntity.builder().id(product.id()).name(product.name()).build();
	}

	public Product toDomain() {
		return new Product(this.id, this.name);
	}

}
