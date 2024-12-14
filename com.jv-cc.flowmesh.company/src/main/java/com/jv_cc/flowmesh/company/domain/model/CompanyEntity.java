package com.jv_cc.flowmesh.company.domain.model;

import io.hypersistence.utils.hibernate.id.Tsid;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "p_company")
public class CompanyEntity {

    @Id @Tsid
    @Column(name = "company_id")
    private Long id;

    @Column(name = "hub_id", nullable = false)
    private Long hubId;

    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private CompanyType type;

    @Column(name = "address", nullable = false)
    private String address;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "created_by", updatable = false)
    private Long createdBy;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "updated_by")
    private Long updatedBy;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Column(name = "deleted_by")
    private Long deletedBy;

    @NotNull
    @ColumnDefault("false")
    @Column(name = "is_deleted")
    private boolean isDeleted = false;

    /*
    * TODO: deletedBy = userId 추가
    */
    public void markAsDelete() {
        this.isDeleted = true;
        this.deletedAt = LocalDateTime.now();
    }

    public CompanyEntity(Long hubId, String name, CompanyType type, String address, Long userId) {
        this.hubId = hubId;
        this.name = name;
        this.type = type;
        this.address = address;
        this.createdBy = userId;
        this.updatedBy = userId;
    }

    public void update(Long hubId, String name, CompanyType type, String address) {
        this.hubId = hubId;
        this.name = name;
        this.type = type;
        this.address = address;
    }
}
