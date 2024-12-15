package com.jv_cc.flowmesh.delivery.domain.model;

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
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "p_delivery_route")
public class DeliveryRouteEntity {

    @Id
    @Tsid
    @Column(name = "delivery_route_id")
    private Long deliveryRouteId;

    @Tsid
    @Column(name = "delivery_id", nullable = false)
    private Long deliveryId;

    @Tsid
    @Column(name = "start_hub_id", nullable = false)
    private Long startHubId;

    @Tsid
    @Column(name = "end_hub_id", nullable = false)
    private Long endHubId;

    @Tsid
    @Column(name = "dm_id")
    private Long deliveryManagerId;

    @Column(name = "delivery_sequence", nullable = false)
    private Long deliverySequence;

    @Column(name = "current_status", nullable = false)
    private DeliveryEnum currentStatus;

    @Column(name = "estimate_duration", nullable = false)
    private Long estimateDuration;

    @Column(name = "estimate_distance", nullable = false)
    private double estimateDistance;

    @Column(name = "actual_duration")
    private Long actualDuration;

    @Column(name = "actual_distance")
    private double actualDistance;

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
