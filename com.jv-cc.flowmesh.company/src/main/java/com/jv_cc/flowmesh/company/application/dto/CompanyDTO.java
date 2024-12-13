package com.jv_cc.flowmesh.company.application.dto;

import com.jv_cc.flowmesh.company.domain.model.CompanyEntity;
import com.jv_cc.flowmesh.company.domain.model.CompanyType;
import com.jv_cc.flowmesh.company.presentation.request.ReqCompanyPostDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDTO {

    private Long companyId;
    private Long hubId;
    private String name;
    private CompanyType type;
    private String address;

    public static CompanyEntity toEntity(ReqCompanyPostDTO dto){
        return CompanyEntity.builder()
                .hubId(dto.getHubId())
                .name(dto.getName())
                .type(dto.getType())
                .address(dto.getAddress())
                .build();
    }

    public static CompanyDTO of(CompanyEntity companyEntity){
        return CompanyDTO.builder()
                .companyId(companyEntity.getId())
                .hubId(companyEntity.getHubId())
                .name(companyEntity.getName())
                .type(companyEntity.getType())
                .address(companyEntity.getAddress())
                .build();
    }

}
