package com.jv_cc.flowmesh.deliverymanager.application.dto;

import com.jv_cc.flowmesh.deliverymanager.domain.model.DeliveryManagerEntity;
import com.jv_cc.flowmesh.deliverymanager.domain.model.DeliveryManagerEnum;
import com.jv_cc.flowmesh.deliverymanager.presentation.request.DeliveryManagerPostRequestDTO;
import com.jv_cc.flowmesh.deliverymanager.presentation.response.DeliveryManagerPostResponseDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
public class DeliveryManagerCreateDTO {

    private Long userId;
    private Long hubId;
    private DeliveryManagerEnum deliveryType;
    @Setter
    private Long deliverySequence;

    private Long deliveryManagerId;
    private LocalDateTime createdAt;

    public DeliveryManagerEntity toEntity(){
        return DeliveryManagerEntity.builder()
                .deliveryManagerId(this.userId)
                .hubId(this.hubId)
                .type(this.deliveryType)
                .userId(this.userId)
                .sequence(this.deliverySequence)
                .build();
    }

    public DeliveryManagerPostResponseDTO toResponseDTO(){
        return DeliveryManagerPostResponseDTO.builder()
                .deliveryManagerId(this.deliveryManagerId)
                .createdAt(this.createdAt)
                .build();
    }

    public DeliveryManagerCreateDTO(DeliveryManagerEntity entity){
        this.userId = null;
        this.hubId = null;
        this.deliveryType = null;
        this.deliverySequence = null;
        this.deliveryManagerId = entity.getDeliveryManagerId();
        this.createdAt = entity.getCreatedAt();
    }


    public DeliveryManagerCreateDTO(DeliveryManagerPostRequestDTO dto){
        this.userId = dto.getUserId();
        this.hubId = dto.getHubId();
        this.deliveryType = dto.getDeliveryType();
        this.deliverySequence = null;
        this.deliveryManagerId = null;
        this.createdAt = null;
    }

}
