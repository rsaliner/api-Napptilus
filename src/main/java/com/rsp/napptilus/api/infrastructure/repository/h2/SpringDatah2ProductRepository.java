package com.rsp.napptilus.api.infrastructure.repository.h2;

import java.math.BigInteger;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.rsp.napptilus.api.infrastructure.repository.h2.entity.ProductEntity;

@Repository
public interface SpringDatah2ProductRepository extends ListCrudRepository<ProductEntity, BigInteger> {

}
