package com.jv_cc.flowmesh.delivery.domain.service;

import com.jv_cc.flowmesh.delivery.application.dto.DeliveryCreateDTO;
import com.jv_cc.flowmesh.delivery.application.dto.DeliveryDTO;
import com.jv_cc.flowmesh.delivery.application.exception.NotCreatedDeliveryException;
import com.jv_cc.flowmesh.delivery.domain.repository.DeliveryRepository;
import com.jv_cc.flowmesh.delivery.infrastructure.AiClient;
import com.jv_cc.flowmesh.delivery.infrastructure.HubClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DeliveryService {
    private final HubClient hubClient;
    private final AiClient aiClient;
    private final DeliveryRepository deliveryRepository;

    public DeliveryCreateDTO createDelivery(DeliveryCreateDTO deliveryCreateDTO) {
        // Hub Feignclient
        hubClient.getChangeHub();

        // Ai Feignclient
        aiClient.createDeadline();

        if(false){
            throw new NotCreatedDeliveryException();
        }

        // Entity 생성

        return null;
    }

    public DeliveryDTO updateDelivery(DeliveryDTO deliveryDTO) {

        return null;
    }

    public Long deleteDelivery(Long deliveryId) {

        return null;
    }

    public DeliveryDTO getOrders() {

        return null;
    }

    public DeliveryDTO getOrder(Long deliveryId) {

        return null;
    }
}
