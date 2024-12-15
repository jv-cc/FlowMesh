package com.jv_cc.flowmesh.product.application.dto;

import com.jv_cc.flowmesh.product.domain.model.ProductEntity;
import com.jv_cc.flowmesh.product.presentation.request.ReqProductPostDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private Long productId;
    private Long hubId;
    private Long companyId;
    private String name;
    private int price;
    private int quantity;

    public static ProductEntity toEntity(ReqProductPostDTO dto) {
        return ProductEntity.builder()
                .hubId(dto.getHubId())
                .companyId(dto.getCompanyId())
                .name(dto.getName())
                .price(dto.getPrice())
                .quantity(dto.getQuantity())
                .build();
    }

    public static ProductDTO of(ProductEntity entity) {
        return ProductDTO.builder()
                .productId(entity.getId())
                .hubId(entity.getHubId())
                .companyId(entity.getCompanyId())
                .name(entity.getName())
                .price(entity.getPrice())
                .quantity(entity.getQuantity())
                .build();
    }
}
