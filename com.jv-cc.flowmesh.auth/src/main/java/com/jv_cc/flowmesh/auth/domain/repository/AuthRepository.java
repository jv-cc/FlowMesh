package com.jv_cc.flowmesh.auth.domain.repository;

import com.jv_cc.flowmesh.auth.domain.model.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<Auth, Long> {
}
