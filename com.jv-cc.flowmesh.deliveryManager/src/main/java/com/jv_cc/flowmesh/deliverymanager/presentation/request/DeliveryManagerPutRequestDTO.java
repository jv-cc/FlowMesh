package com.jv_cc.flowmesh.deliverymanager.presentation.request;


import com.jv_cc.flowmesh.deliverymanager.domain.model.DeliveryManagerEnum;
import lombok.Builder;
import lombok.Getter;

@Getter
public class DeliveryManagerPutRequestDTO {

    private Long hubId;
    private DeliveryManagerEnum type;
}
