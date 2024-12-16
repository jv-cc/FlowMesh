package com.jv_cc.flowmesh.order_service.application.service;

import com.jv_cc.flowmesh.order_service.application.dto.OrderCreateDTO;
import com.jv_cc.flowmesh.order_service.application.dto.OrderDTO;
import com.jv_cc.flowmesh.order_service.application.dto.OrderGetOneDTO;
import com.jv_cc.flowmesh.order_service.application.dto.OrderPutDTO;
import com.jv_cc.flowmesh.order_service.application.exception.DeliveryFailureException;
import com.jv_cc.flowmesh.order_service.application.exception.OrderNotChangedException;
import com.jv_cc.flowmesh.order_service.application.exception.ProductCountFailureException;
import com.jv_cc.flowmesh.order_service.application.exception.ProductNotFoundException;
import com.jv_cc.flowmesh.order_service.domain.model.OrderEntity;
import com.jv_cc.flowmesh.order_service.domain.model.OrderStatusEnum;
import com.jv_cc.flowmesh.order_service.domain.repository.OrderRepository;
import com.jv_cc.flowmesh.order_service.infrastructure.DeliveryClient;
import com.jv_cc.flowmesh.order_service.infrastructure.ProductClient;
import com.jv_cc.flowmesh.order_service.presentation.request.DeliveryPostDTO;
import com.jv_cc.flowmesh.order_service.presentation.response.*;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final DeliveryClient deliveryClient;
    private final ProductClient productClient;

    @Transactional
    public OrderCreateDTO createOrder(OrderCreateDTO orderDTO) {
        OrderEntity entity = orderRepository.save(orderDTO.toEntity());

        // 배송 생성
        DeliveryPostDTO deliveryPostDTO = DeliveryPostDTO.builder()
                .orderId(entity.getOrderId())
                .demandCompanyId(entity.getDemandId())
                .supplyCompanyId(entity.getSupplyId())
                .productId(entity.getProductId())
                .count(entity.getCount())
                .requestInfo(orderDTO.getRequestInfo())
                .build();
        ResponseDTO<DeliveryPostResponseDTO> responseDeliveryDTO = deliveryClient.createDelivery(deliveryPostDTO);

        if (responseDeliveryDTO.getData().getDeliveryId() == null) {
            throw new DeliveryFailureException();
        }

        // 재고 차감
        ResponseDTO<ProductPatchResponseDTO> responseProductDTO = productClient.updateProduct(entity.getProductId(), -1 * entity.getCount());

        if (responseProductDTO.getData().getProductId() == null) {
            throw new ProductCountFailureException();
        }
        OrderCreateDTO newOrderDTO = new OrderCreateDTO(entity);
        return newOrderDTO;
    }

    public OrderPutDTO updateOrder(OrderPutDTO orderDto) {
        OrderEntity orderEntity = orderRepository.findById(orderDto.getOrderId()).orElseThrow(IllegalArgumentException::new);

        if( !orderEntity.getStatus().equals(OrderStatusEnum.ORDER_RECEIPT)){
            throw new OrderNotChangedException();
        }

        ProductGetResponseDTO getProductInfo = productClient.getProduct(orderEntity.getProductId()).getData();

        if (getProductInfo.getProductId() == null) {
            throw new ProductNotFoundException();
        }

        ProductPatchResponseDTO updateProductInfo = productClient.updateProduct(orderEntity.getProductId(), getProductInfo.getQuantity() - (orderEntity.getCount() - orderDto.getCount())).getData();

        if (updateProductInfo.getProductId() == null) {
            throw new ProductNotFoundException();
        }

        orderEntity.changeProductCount(orderDto.getCount());

        OrderPutDTO newOrderDTO = new OrderPutDTO(orderEntity);

        return newOrderDTO;
    }

    /*
    * TODO : 주문접수 이외의 상태에서 작업 추가해야 함
    *  예를 들면, 배송완료시 반품 접수로 진행해야 하고 메세지를 남겨야 함
    */
    public Map<String, Object> deleteOrder(Long orderId) {
        OrderEntity entity = orderRepository.findById(orderId).orElseThrow(IllegalArgumentException::new);

        if(!entity.getStatus().equals(OrderStatusEnum.ORDER_RECEIPT)){
            throw new OrderNotChangedException();
        }

        entity.markAsDelete();

        // delete 성공시 메세지를 보내야 함, 아니면 이벤트 처리를 해야 함

        Map<String, Object> result = new HashMap<>();
        result.put("order_id", entity.getOrderId());
        result.put("deleted_at", entity.getDeletedAt());

        return result;
    }

    public Page<OrderGetResponseDTO> getOrders(Pageable pageable) {
        return orderRepository.findAll(pageable)
                .map(OrderGetResponseDTO::new);
    }

    public OrderGetOneResponseDTO getOrder(Long orderId) {
        OrderEntity entity = orderRepository.findById(orderId).orElseThrow(IllegalArgumentException::new);

        //deliveryId 가져와야 함 --> order테이블에 deliveryId를 넣어야 할 듯
        DeliveryGetResponseDTO delivery = deliveryClient.getDelivery(entity.getOrderId()).getData();

        OrderGetOneResponseDTO dto = new OrderGetOneResponseDTO(entity, delivery.getDeliveryId());

        return dto;
    }
}
