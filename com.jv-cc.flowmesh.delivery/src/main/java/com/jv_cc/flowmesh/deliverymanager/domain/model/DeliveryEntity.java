package com.jv_cc.flowmesh.deliverymanager.domain.model;

import io.hypersistence.utils.hibernate.id.Tsid;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "p_delivery")
public class DeliveryEntity {

    @Id @Tsid
    @Column(name = "delivery_id")
    private Long deliveryId;

    @Tsid
    @Column(name = "order_id", nullable = false)
    private Long orderId;

    @Tsid
    @Column(name = "delivery_manager_id", nullable = false)
    private Long deliveryManagerId;

    @Tsid
    @Column(name = "start_hub_id", nullable = false)
    private Long startHubId;

    @Tsid
    @Column(name = "end_hub_id", nullable = false)
    private Long endHubId;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "recipient")
    private String recipient;

    @Tsid
    @Column(name = "recipient_id")
    private Long recipientId;

    @Column(name = "current_status", nullable = false)
    private DeliveryEnum currentStatus;

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
