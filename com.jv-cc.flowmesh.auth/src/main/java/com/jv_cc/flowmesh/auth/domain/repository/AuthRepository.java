package com.jv_cc.flowmesh.auth.domain.repository;

import com.jv_cc.flowmesh.auth.domain.model.Auth;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRepository extends JpaRepository<Auth, Long> {
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByNickname(String nickname);

    boolean existsBySlackId(String slackId);

    Optional<Auth> findByUsernameAndIsDeletedFalse(String username);

    Optional<Auth> findByIdAndIsDeletedFalse(Long id);

    Optional<Auth> findByRefreshTokenAndIsDeletedFalse(String refreshToken);

    Page<Auth> findAllByUsernameAndIsDeletedFalse(String username, Pageable pageable);
}
