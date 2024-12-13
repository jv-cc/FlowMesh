package com.jv_cc.flowmesh.auth.domain.model;

import io.hypersistence.utils.hibernate.id.Tsid;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "p_auth")
public class Auth {

    @Id @Tsid
    @Column(name = "user_id")
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Column(name = "slack_id", nullable = false)
    private String slackId;

    @Column(name = "role", nullable = false)
    private UserRoleEnum role;

    @Column(name = "refresh_token")
    private String refreshToken;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "updated_by")
    private Long updatedBy;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Column(name = "deleted_by")
    private Long deletedBy;

    @NotNull
    @ColumnDefault("false")
    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = false;

    public LocalDateTime markAsDelete(Long userId) {
        this.isDeleted = true;
        this.deletedAt = LocalDateTime.now();
        this.deletedBy = userId;
        return this.deletedAt;
    }

    public LocalDateTime updateUser (Long masterId, String nickname, String email, String slackId) {
        this.nickname = nickname;
        this.email = email;
        this.slackId = slackId;
        this.updatedAt = LocalDateTime.now();
        this.updatedBy = masterId;
        return this.updatedAt;
    }

    @Builder
    public Auth(String username, String password, String email, String nickname, String slackId) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.nickname = nickname;
        this.slackId = slackId;
        this.role = UserRoleEnum.CUSTOMER;
        this.createdAt = LocalDateTime.now();
    }

    public void updateRefreshToken(String refresh_token) {
        this.refreshToken = refresh_token;
    }

}

