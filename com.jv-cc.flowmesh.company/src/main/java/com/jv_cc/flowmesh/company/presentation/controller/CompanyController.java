package com.jv_cc.flowmesh.company.presentation.controller;

import com.jv_cc.flowmesh.company.application.service.CompanyService;
import com.jv_cc.flowmesh.company.infrastructure.swagger.CompanyControllerSwagger;
import com.jv_cc.flowmesh.company.presentation.request.ReqCompanyPostDTO;
import com.jv_cc.flowmesh.company.presentation.response.ResCompanyDTO;
import com.jv_cc.flowmesh.company.presentation.response.ResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/company")
public class CompanyController implements CompanyControllerSwagger {

    private final CompanyService companyService;

    @PostMapping
    public ResponseEntity<ResDTO<ResCompanyDTO>> createCompany(@RequestBody ReqCompanyPostDTO dto) {
        return new ResponseEntity<>(
                ResDTO.<ResCompanyDTO>builder()
                        .code(HttpStatus.CREATED.value())
                        .message("업체가 생성되었습니다.")
                        .data(new ResCompanyDTO(companyService.createCompany(dto).getCompanyId()))
                        .build(),
                HttpStatus.CREATED
        );
    }

    @PatchMapping("/{companyId}")
    public ResponseEntity<ResDTO<ResCompanyDTO>> modifyCompany(@PathVariable Long companyId, @RequestBody ReqCompanyPostDTO dto) {
        return new ResponseEntity<>(
                ResDTO.<ResCompanyDTO>builder()
                        .code(HttpStatus.OK.value())
                        .message("업체가 수정되었습니다.")
                        .data(new ResCompanyDTO(companyService.modifyCompany(companyId, dto).getCompanyId()))
                        .build(),
                HttpStatus.OK
        );
    }
}
