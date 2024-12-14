package com.jv_cc.flowmesh.product.domain.repository;

import com.jv_cc.flowmesh.product.domain.model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {


    boolean existsByNameAndIsDeletedFalse(String name);

    Optional<ProductEntity> findByIdAndIsDeletedFalse(Long productId);
}
