package com.jv_cc.flowmesh.deliverymanager.presentation.request;

import com.jv_cc.flowmesh.deliverymanager.domain.model.DeliveryManagerEnum;
import lombok.Getter;

@Getter
public class DeliveryManagerPostRequestDTO {

    private Long userId;
    private Long hubId;
    private DeliveryManagerEnum deliveryType;

}
