package com.jv_cc.flowmesh.delivery.domain.model;

import io.hypersistence.utils.hibernate.id.Tsid;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "p_delivery")
public class DeliveryEntity {

    @Id @Tsid
    @Column(name = "delivery_id")
    private Long deliveryId;

    @Tsid
    @Column(name = "order_id", nullable = false)
    private Long orderId;

    @OneToMany(mappedBy = "delivery", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<DeliveryRouteEntity> deliveryRoutes = new ArrayList<>();

    @Tsid
    @Column(name = "dm_id")
    private Long deliveryManagerId;     //업체 배송 담당자 => 생성시 확정하면 안 됨

    @Tsid
    @Column(name = "start_hub_id", nullable = false)
    private Long startHubId;

    @Tsid
    @Column(name = "end_hub_id", nullable = false)
    private Long endHubId;

    @Column(name = "address", nullable = false)
    private String address;

    @Tsid
    @Column(name = "recipient_id", nullable = false)
    private Long recipientId;

    @Tsid
    @Column(name = "message_id", nullable = false)
    private Long messageId;

    @Enumerated(EnumType.STRING)
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
