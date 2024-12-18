package com.jv_cc.flowmesh.order_service.infrastructure.feignclient;

import com.jv_cc.flowmesh.order_service.presentation.request.DeliveryPostDTO;
import com.jv_cc.flowmesh.order_service.presentation.response.DeliveryGetResponseDTO;
import com.jv_cc.flowmesh.order_service.presentation.response.DeliveryPostResponseDTO;
import com.jv_cc.flowmesh.order_service.presentation.response.ResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "delivery")
public interface DeliveryClient {

    @PostMapping("/api/deliveries")
    ResponseDTO<DeliveryPostResponseDTO> createDelivery(@RequestBody DeliveryPostDTO dto);

    @GetMapping("/api/deliveries/{delivery_id}")
    ResponseDTO<DeliveryGetResponseDTO> getDelivery(@PathVariable Long delivery_id);

}
