package com.jv_cc.flowmesh.deliverymanager.domain.model;

import io.hypersistence.utils.hibernate.id.Tsid;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
@Table(name = "p_delivery_manager")
public class DeliveryManagerEntity {

    @Id @Tsid
    @Column(name = "delivery_manager_id")
    private Long deliveryManagerId;

    @Tsid
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Tsid
    @Column(name = "hub_id", nullable = false)
    private Long hubId;

    @Column(name = "type", nullable = false)
    private DeliveryManagerEnum type;

    /*
     * TODO: 자동 값 상승하는 방법 찾기 GeneratedValue 안 먹힘
     * */
    @Column(name = "sequence", nullable = false)
    private Long sequence;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "created_by", nullable = false, updatable = false)
    private Long createdBy;

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
    @Column(name = "is_deleted")
    private boolean isDeleted = false;

    /*
     * TODO: deletedBy = userId 추가
     * */
    public void markAsDelete() {
        this.deletedAt = LocalDateTime.now();
        this.isDeleted = true;
    }


}
