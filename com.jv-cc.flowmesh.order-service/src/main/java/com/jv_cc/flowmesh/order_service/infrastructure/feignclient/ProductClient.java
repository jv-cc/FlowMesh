package com.jv_cc.flowmesh.order_service.infrastructure.feignclient;

import com.jv_cc.flowmesh.order_service.presentation.response.ProductGetResponseDTO;
import com.jv_cc.flowmesh.order_service.presentation.response.ProductPatchResponseDTO;
import com.jv_cc.flowmesh.order_service.presentation.response.ResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "hub-service")
public interface ProductClient {

    @PatchMapping("/api/products/{product_id}")
    ResponseDTO<ProductPatchResponseDTO> updateProduct(
            @PathVariable("product_id") Long product_id,
            @RequestBody int count
            );

    @GetMapping("/api/products/{product_id}")
    ResponseDTO<ProductGetResponseDTO> getProduct(@PathVariable Long product_id);
}
