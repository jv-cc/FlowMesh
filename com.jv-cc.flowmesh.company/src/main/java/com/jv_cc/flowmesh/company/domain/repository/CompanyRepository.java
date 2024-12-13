package com.jv_cc.flowmesh.company.domain.repository;

import com.jv_cc.flowmesh.company.domain.model.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {


    boolean existsByNameAndIsDeletedFalse(String name);
}
