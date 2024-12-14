package com.jv_cc.flowmesh.company.domain.repository;

import com.jv_cc.flowmesh.company.domain.model.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {


    boolean existsByNameAndIsDeletedFalse(String name);

    Optional<CompanyEntity> findByIdAndIsDeletedFalse(Long companyId);
}
