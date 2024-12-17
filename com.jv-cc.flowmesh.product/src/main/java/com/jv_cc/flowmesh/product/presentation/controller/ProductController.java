package com.jv_cc.flowmesh.product.presentation.controller;

import com.jv_cc.flowmesh.product.application.dto.ProductDTO;
import com.jv_cc.flowmesh.product.application.service.ProductService;
import com.jv_cc.flowmesh.product.infrastructure.swagger.ProductControllerSwagger;
import com.jv_cc.flowmesh.product.presentation.request.ReqProductPostDTO;
import com.jv_cc.flowmesh.product.presentation.response.ResDTO;
import com.jv_cc.flowmesh.product.presentation.response.ResProductDTO;
import com.jv_cc.flowmesh.product.presentation.response.ResProductGetDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
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

    @GetMapping("/{productId}")
    public ResponseEntity<ResDTO<ResProductGetDTO>> getProduct(@PathVariable Long productId) {

        ProductDTO dto = productService.getProduct(productId);

        return new ResponseEntity<>(
                ResDTO.<ResProductGetDTO>builder()
                        .code(HttpStatus.OK.value())
                        .message("상품 조회에 성공했습니다.")
                        .data(ResProductGetDTO.builder()
                                .productId(dto.getCompanyId())
                                .hubId(dto.getHubId())
                                .companyId(dto.getCompanyId())
                                .name(dto.getName())
                                .price(dto.getPrice())
                                .quantity(dto.getQuantity())
                                .build())
                        .build(),
                HttpStatus.OK
        );
    }

    @PatchMapping("/{productId}")
    public ResponseEntity<ResDTO<ResProductDTO>> modifyProduct(@PathVariable Long productId, @RequestBody ReqProductPostDTO dto) {
        return new ResponseEntity<>(
                ResDTO.<ResProductDTO>builder()
                        .code(HttpStatus.OK.value())
                        .message("상품이 수정되었습니다.")
                        .data(new ResProductDTO(productService.modifyProduct(productId, dto).getProductId()))
                        .build(),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<ResDTO<ResProductDTO>> deleteProduct(@PathVariable Long productId) {
        return new ResponseEntity<>(
                ResDTO.<ResProductDTO>builder()
                        .code(HttpStatus.OK.value())
                        .message("상품이 삭제되었습니다.")
                        .data(new ResProductDTO(productService.deleteProduct(productId).getProductId()))
                        .build(),
                HttpStatus.OK
        );
    }
}
