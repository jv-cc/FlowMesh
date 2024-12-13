package com.jv_cc.flowmesh.deliverymanager.domain.model;


import io.hypersistence.utils.hibernate.id.Tsid;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "p_delivery_route")
public class DeliveryRoutesEntity {

    @Id @Tsid
    @Column(name = "route_id")
    private String routeId;

    @Tsid
    @Column(name = "start_hub_id", nullable = false)
    private String startHubId;

    @Tsid
    @Column(name = "end_hub_id", nullable = false)
    private String endHubId;

    @Tsid
    @Column(name = "delivery_id", nullable = false)
    private String deliveryId;

    @Tsid
    @Column(name = "delivery_manager_id", nullable = false)
    private String deliveryManagerId;

    @Column(name = "sequence", nullable = false)
    private int sequence;

    @Column(name = "current_status", nullable = false)
    private String currentStatus;

    @Column(name = "estimate_duration", nullable = false)
    private LocalDateTime estimateDuration;

    @Column(name = "estimate_distance", nullable = false)
    private Long estimateDistance;

    @Column(name = "actual_duration")
    private LocalDateTime actualDuration;

    @Column(name = "actual_distance")
    private Long actualDistance;

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
