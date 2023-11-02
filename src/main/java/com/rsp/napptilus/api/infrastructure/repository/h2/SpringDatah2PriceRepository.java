package com.rsp.napptilus.api.infrastructure.repository.h2;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.rsp.napptilus.api.infrastructure.repository.h2.entity.BrandEntity;
import com.rsp.napptilus.api.infrastructure.repository.h2.entity.PriceEntity;
import com.rsp.napptilus.api.infrastructure.repository.h2.entity.ProductEntity;

@Repository
public interface SpringDatah2PriceRepository extends ListCrudRepository<PriceEntity, BigInteger> {

	@Query("select p from PriceEntity p where ?1 between p.startDate and p.endDate and p.product = ?2 and p.brand = ?3 order by priority DESC Limit 1")
	Optional<PriceEntity> findFirstByAndSort(LocalDateTime date, ProductEntity product, BrandEntity brand);

}
