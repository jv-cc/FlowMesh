package com.jv_cc.flowmesh.deliverymanager.domain.service;

import com.jv_cc.flowmesh.deliverymanager.application.dto.DeliveryManagerCreateDTO;
import com.jv_cc.flowmesh.deliverymanager.application.dto.DeliveryManagerDTO;
import com.jv_cc.flowmesh.deliverymanager.application.dto.DeliveryManagerPutDTO;
import com.jv_cc.flowmesh.deliverymanager.application.exception.*;
import com.jv_cc.flowmesh.deliverymanager.domain.model.DeliveryManagerEntity;
import com.jv_cc.flowmesh.deliverymanager.domain.model.DeliveryManagerEnum;
import com.jv_cc.flowmesh.deliverymanager.domain.model.UserRoleEnum;
import com.jv_cc.flowmesh.deliverymanager.domain.repository.DeliveryManagerRepository;
import com.jv_cc.flowmesh.deliverymanager.infrastructure.AuthClient;
import com.jv_cc.flowmesh.deliverymanager.infrastructure.HubClient;
import com.jv_cc.flowmesh.deliverymanager.presentation.response.DeliveryManagerGetOneResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeliveryManagerService {

    private final DeliveryManagerRepository deliveryManagerRepository;
    private final HubClient hubClient;
    private final AuthClient authClient;
    private final int MAX_DELIVERY_MANGER_COUNT = 10;

    /**
     * TODO : 권한 빼내는 로직 작성 / 허브 관리자가 담당 허브인지 확인하는 로직 추가예정
     */
    public DeliveryManagerCreateDTO createDeliveryManager(DeliveryManagerCreateDTO dto) {

        authClient.checkRole(dto.getUserId());
        // ...
        UserRoleEnum role = UserRoleEnum.HUB_MANAGER;
        if (!role.equals(UserRoleEnum.MASTER) || !role.equals(UserRoleEnum.HUB_MANAGER)){
            throw new NotPermissionException();
        }

        if(!hubClient.existsHubBy(dto.getHubId())){
            throw new NotFoundHubException();
        }

        if(checkDeliveryManagerCount(dto) > MAX_DELIVERY_MANGER_COUNT ){
            throw new ExceededCapacityException();
        }

        Long maxSequence = deliveryManagerRepository.findMaxSequenceByHubIdAndType(dto.getHubId(), dto.getDeliveryType());

        dto.setDeliverySequence(maxSequence + 1);
        DeliveryManagerEntity entity = deliveryManagerRepository.save(dto.toEntity());

        DeliveryManagerCreateDTO deliveryManagerCreateDTO = new DeliveryManagerCreateDTO(entity);

        return deliveryManagerCreateDTO;
    }

    private int checkDeliveryManagerCount(DeliveryManagerCreateDTO dto) {
        int capacity = 0;

        if (dto.getDeliveryType().equals(DeliveryManagerEnum.COMPANY_DELIVERY)) {
            capacity = deliveryManagerRepository.countByHubIdAndIsDeletedFalse(dto.getHubId());
        }else if (dto.getDeliveryType().equals(DeliveryManagerEnum.HUB_DELIVERY)){
            capacity = deliveryManagerRepository.countByDeliveryManagerIdAndIsDeletedFalse(DeliveryManagerEnum.HUB_DELIVERY);
        }else{
            throw new NotFoundDeliveryManagerTypeException();
        }

        return capacity;
    }

    public DeliveryManagerPutDTO updateDeliveryManager(DeliveryManagerPutDTO dto) {

        DeliveryManagerEntity entity = deliveryManagerRepository.findById(dto.getDeliveryManagerId()).orElseThrow(NotFoundDeliveryManagerException::new);
        if(!hubClient.existsHubBy(dto.getHubId())){
            throw new NotFoundHubException();
        }
        entity.changeHubId(dto.getHubId());
        entity.changeType(dto.getType());

        DeliveryManagerPutDTO newDto = new DeliveryManagerPutDTO(entity);

        return newDto;
    }

    public Map<String, Object> deleteDeliveryManager(Long deliveryManagerId) {
        DeliveryManagerEntity entity = deliveryManagerRepository.findById(deliveryManagerId).orElseThrow(IllegalArgumentException::new);

        entity.markAsDelete();

        Map<String, Object> result = new HashMap<>();
        result.put("order_id", entity.getDeliveryManagerId());
        result.put("deleted_at", entity.getDeletedAt());

        return result;
    }

    public Page<DeliveryManagerGetOneResponseDTO> getOrders(Pageable pageable) {
        return deliveryManagerRepository.findAll(pageable)
                .map(DeliveryManagerGetOneResponseDTO::new);
    }

    public DeliveryManagerGetOneResponseDTO getOrder(Long deliveryManagerId) {
        DeliveryManagerEntity deliveryManagerEntity = deliveryManagerRepository.findById(deliveryManagerId).orElseThrow(IllegalArgumentException::new);

        return new DeliveryManagerGetOneResponseDTO(deliveryManagerEntity);
    }
}
