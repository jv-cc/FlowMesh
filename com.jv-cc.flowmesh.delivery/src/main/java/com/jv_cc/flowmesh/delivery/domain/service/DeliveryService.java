package com.jv_cc.flowmesh.delivery.domain.service;

import com.jv_cc.flowmesh.delivery.application.dto.DeliveryCreateDTO;
import com.jv_cc.flowmesh.delivery.application.dto.DeliveryDTO;
import com.jv_cc.flowmesh.delivery.application.dto.DeliveryDeleteDTO;
import com.jv_cc.flowmesh.delivery.application.dto.DeliveryPutDTO;
import com.jv_cc.flowmesh.delivery.application.exception.NotCreatedDeliveryException;
import com.jv_cc.flowmesh.delivery.application.exception.NotDeletedException;
import com.jv_cc.flowmesh.delivery.domain.model.DeliveryEntity;
import com.jv_cc.flowmesh.delivery.domain.model.DeliveryEnum;
import com.jv_cc.flowmesh.delivery.domain.model.DeliveryRouteEntity;
import com.jv_cc.flowmesh.delivery.domain.repository.DeliveryRepository;
import com.jv_cc.flowmesh.delivery.domain.repository.DeliveryRouteRepository;
import com.jv_cc.flowmesh.delivery.infrastructure.feignclient.AiClient;
import com.jv_cc.flowmesh.delivery.infrastructure.feignclient.HubClient;
import com.jv_cc.flowmesh.delivery.presentation.response.DeliveryAllGetResponseDTO;
import com.jv_cc.flowmesh.delivery.presentation.response.DeliveryGetResponseDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DeliveryService {
    private final HubClient hubClient;
    private final AiClient aiClient;
    private final DeliveryRepository deliveryRepository;
    private final DeliveryRouteRepository deliveryRouteRepository;

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

    public DeliveryPutDTO updateDelivery(DeliveryPutDTO deliveryDTO) {



        return null;
    }

    @Transactional
    public DeliveryDeleteDTO deleteDelivery(Long deliveryId) {
        DeliveryEntity deliveryEntity = deliveryRepository.findById(deliveryId).orElseThrow(IllegalArgumentException::new);

        if(!deliveryEntity.getCurrentStatus().equals(DeliveryEnum.Delivery_RECEPTION)){
            throw new NotDeletedException();
        }

        List<DeliveryRouteEntity> deliveryRouteEntities = deliveryRouteRepository.findAllByDeliveryId(deliveryEntity.getDeliveryId());

        deliveryRouteEntities.forEach(deliveryRouteEntity -> {deliveryRouteEntity.markAsDelete();});
        deliveryEntity.markAsDelete();

        DeliveryDeleteDTO dto = new DeliveryDeleteDTO(deliveryEntity.getDeliveryId(), deliveryEntity.getDeletedAt());

        return dto;
    }

    public Page<DeliveryAllGetResponseDTO> getDeliveries(Long orderId, Pageable pageable) {
        if (orderId == null) {
            return deliveryRepository.findAll(pageable).map(deliveryEntity -> new DeliveryAllGetResponseDTO(deliveryEntity));
        }else{
            return deliveryRepository.findByOrderId(orderId, pageable).map(deliveryEntity -> new DeliveryAllGetResponseDTO(deliveryEntity));

        }
    }

    public DeliveryGetResponseDTO getDeliveryById(Long deliveryId) {
        DeliveryEntity deliveryEntity = deliveryRepository.findById(deliveryId).orElseThrow(IllegalArgumentException::new);
        List<DeliveryRouteEntity> deliveryRouteEntities = deliveryRouteRepository.findAllByDeliveryId(deliveryId);

        DeliveryGetResponseDTO deliveryGetResponseDTO = new DeliveryGetResponseDTO(deliveryEntity, deliveryRouteEntities);

        return deliveryGetResponseDTO;
    }
}
