package com.jv_cc.flowmesh.company.application.service;

import com.jv_cc.flowmesh.company.application.dto.CompanyDTO;
import com.jv_cc.flowmesh.company.application.exception.DuplicateCompanyNameException;
import com.jv_cc.flowmesh.company.application.exception.NotFoundHubException;
import com.jv_cc.flowmesh.company.domain.model.CompanyEntity;
import com.jv_cc.flowmesh.company.domain.repository.CompanyRepository;
import com.jv_cc.flowmesh.company.infrastructure.HubClient;
import com.jv_cc.flowmesh.company.presentation.request.ReqCompanyPostDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final HubClient hubClient;

    public CompanyDTO createCompany(ReqCompanyPostDTO dto) {
        log.info("Hub ID: {}", dto.getHubId());

        if(!hubClient.existsHubBy(dto.getHubId())){
            throw new NotFoundHubException();
        }

        if(companyRepository.existsByNameAndIsDeletedFalse(dto.getName())){
            throw new DuplicateCompanyNameException();
        }

        CompanyEntity companyEntity = CompanyDTO.toEntity(dto);

        return CompanyDTO.of(companyRepository.save(companyEntity));
    }
}
