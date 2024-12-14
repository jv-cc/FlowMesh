package com.jv_cc.flowmesh.product.presentation.controller;

import com.jv_cc.flowmesh.product.application.service.ProductService;
import com.jv_cc.flowmesh.product.infrastructure.swagger.ProductControllerSwagger;
import com.jv_cc.flowmesh.product.presentation.request.ReqProductPostDTO;
import com.jv_cc.flowmesh.product.presentation.response.ResDTO;
import com.jv_cc.flowmesh.product.presentation.response.ResProductDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController implements ProductControllerSwagger {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ResDTO<ResProductDTO>> createProduct(@RequestBody ReqProductPostDTO dto) {
        return new ResponseEntity<>(
                ResDTO.<ResProductDTO>builder()
                        .code(HttpStatus.CREATED.value())
                        .message("상품이 생성되었습니다.")
                        .data(new ResProductDTO(productService.createProduct(dto).getProductId()))
                        .build(),
                HttpStatus.CREATED
        );

    }
}
