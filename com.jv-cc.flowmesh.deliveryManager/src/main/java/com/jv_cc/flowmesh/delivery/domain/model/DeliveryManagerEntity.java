package com.jv_cc.flowmesh.delivery.domain.model;

import io.hypersistence.utils.hibernate.id.Tsid;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @Column(name = "slack_id", nullable = false)
    private Long slackId;

    @Column(name = "type", nullable = false)
    private DeliveryManagerEnum type;

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
