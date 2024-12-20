package com.jv_cc.flowmesh.company.application.service;

import com.jv_cc.flowmesh.company.application.dto.CompanyDTO;
import com.jv_cc.flowmesh.company.application.exception.company.DuplicateCompanyNameException;
import com.jv_cc.flowmesh.company.application.exception.company.NotFoundCompanyException;
import com.jv_cc.flowmesh.company.application.exception.hub.NotFoundHubException;
import com.jv_cc.flowmesh.company.domain.model.CompanyEntity;
import com.jv_cc.flowmesh.company.domain.repository.CompanyRepository;
import com.jv_cc.flowmesh.company.infrastructure.HubClient;
import com.jv_cc.flowmesh.company.presentation.request.ReqCompanyPostDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final HubClient hubClient;

    @Transactional
    public CompanyDTO createCompany(ReqCompanyPostDTO dto) {
        if (!hubClient.existsHubBy(dto.getHubId())) {
            throw new NotFoundHubException();
        }

        if (checkDuplicateCompanyName(dto.getName())) {
            throw new DuplicateCompanyNameException();
        }

        CompanyEntity companyEntity = CompanyDTO.toEntity(dto);

        return CompanyDTO.of(companyRepository.save(companyEntity));
    }

    @Transactional(readOnly = true)
    public CompanyDTO getCompany(Long companyId) {

        CompanyEntity companyEntity = getCompanyEntity(companyId);

        return CompanyDTO.of(companyEntity);
    }

    @Transactional
    public CompanyDTO modifyCompany(Long companyId, ReqCompanyPostDTO dto) {

        CompanyEntity companyEntity = getCompanyEntity(companyId);

        if (!hubClient.existsHubBy(dto.getHubId())) {
            throw new NotFoundHubException();
        }

        if (checkDuplicateCompanyName(dto.getName())) {
            throw new DuplicateCompanyNameException();
        }

        companyEntity.update(dto.getHubId(),dto.getName(), dto.getType(), dto.getAddress());

        return CompanyDTO.of(companyEntity);
    }

    @Transactional
    public CompanyDTO deleteCompany(Long companyId) {

        CompanyEntity companyEntity = getCompanyEntity(companyId);

        companyEntity.markAsDelete();

        return CompanyDTO.of(companyEntity);
    }

    private CompanyEntity getCompanyEntity(Long companyId) {
        return companyRepository.findByIdAndIsDeletedFalse(companyId).orElseThrow(NotFoundCompanyException::new);
    }

    public boolean getCompanyIdBy(Long companyId) {
        return companyRepository.existsByIdAndIsDeletedFalse(companyId);
    }

    private boolean checkDuplicateCompanyName(String companyName) {
        return companyRepository.existsByNameAndIsDeletedFalse(companyName);
    }
}
