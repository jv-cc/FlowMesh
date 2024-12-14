package com.jv_cc.flowmesh.deliverymanager.application.service;

import com.jv_cc.flowmesh.deliverymanager.application.dto.DeliveryManagerCreateDTO;
import com.jv_cc.flowmesh.deliverymanager.application.dto.DeliveryManagerDTO;
import com.jv_cc.flowmesh.deliverymanager.application.exception.ExceededCapacityException;
import com.jv_cc.flowmesh.deliverymanager.application.exception.NotFoundDeliveryManagerTypeException;
import com.jv_cc.flowmesh.deliverymanager.application.exception.NotFoundHubException;
import com.jv_cc.flowmesh.deliverymanager.application.exception.NotPermissionException;
import com.jv_cc.flowmesh.deliverymanager.domain.model.DeliveryManagerEntity;
import com.jv_cc.flowmesh.deliverymanager.domain.model.DeliveryManagerEnum;
import com.jv_cc.flowmesh.deliverymanager.domain.model.UserRoleEnum;
import com.jv_cc.flowmesh.deliverymanager.domain.repository.DeliveryManagerRepository;
import com.jv_cc.flowmesh.deliverymanager.infrastructure.AuthClient;
import com.jv_cc.flowmesh.deliverymanager.infrastructure.HubClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public DeliveryManagerDTO updateDeliveryManager(DeliveryManagerDTO deliveryManagerDTO) {

        return null;
    }

    public Long deleteDeliveryManager(Long deliveryManagerId) {

        return null;
    }

    public DeliveryManagerDTO getOrders() {

        return null;
    }

    public DeliveryManagerDTO getOrder(Long deliveryManagerId) {

        return null;
    }
}
