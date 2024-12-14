package com.jv_cc.flowmesh.product.application.service;

import com.jv_cc.flowmesh.product.application.dto.ProductDTO;
import com.jv_cc.flowmesh.product.application.exception.company.NotFoundCompanyException;
import com.jv_cc.flowmesh.product.application.exception.hub.NotFoundHubException;
import com.jv_cc.flowmesh.product.application.exception.product.DuplicateProductNameException;
import com.jv_cc.flowmesh.product.domain.model.ProductEntity;
import com.jv_cc.flowmesh.product.domain.repository.ProductRepository;
import com.jv_cc.flowmesh.product.infrastructure.client.CompanyClient;
import com.jv_cc.flowmesh.product.infrastructure.client.HubClient;
import com.jv_cc.flowmesh.product.presentation.request.ReqProductPostDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final HubClient hubClient;
    private final CompanyClient companyClient;

    @Transactional
    public ProductDTO createProduct(ReqProductPostDTO dto) {

        if (!hubClient.existsHubBy(dto.getHubId())) {
            throw new NotFoundHubException();
        }

        if(!companyClient.existsCompanyBy(dto.getCompanyId())) {
            throw new NotFoundCompanyException();
        }

        if(productRepository.existsByNameAndIsDeletedFalse(dto.getName())){
            throw new DuplicateProductNameException();
        }

        ProductEntity productEntity = ProductDTO.toEntity(dto);

        return ProductDTO.of(productRepository.save(productEntity));
    }
}
