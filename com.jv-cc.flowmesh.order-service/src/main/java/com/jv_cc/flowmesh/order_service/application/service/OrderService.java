package com.jv_cc.flowmesh.order_service.application.service;

import com.jv_cc.flowmesh.order_service.application.dto.OrderDTO;
import com.jv_cc.flowmesh.order_service.domain.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderDTO createOrder(OrderDTO orderDTO) {

        return null;
    }

    public OrderDTO updateOrder(OrderDTO orderDto) {

        return null;
    }

    public Long deleteOrder(Long orderId) {

        return null;
    }

    public OrderDTO getOrders() {

        return null;
    }

    public OrderDTO getOrder(Long orderId) {

        return null;
    }
}
