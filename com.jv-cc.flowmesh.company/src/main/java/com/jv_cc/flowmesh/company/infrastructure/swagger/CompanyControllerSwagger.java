package com.jv_cc.flowmesh.company.infrastructure.swagger;

import com.jv_cc.flowmesh.company.presentation.request.ReqCompanyPostDTO;
import com.jv_cc.flowmesh.company.presentation.response.ResCompanyDTO;
import com.jv_cc.flowmesh.company.presentation.response.ResDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Company", description = "업체 생성, 검색, 수정, 삭제 관련 사용자 API")
public interface CompanyControllerSwagger {

    @Operation(summary = "업체 생성", description = "사용자 ID를 통해 업체를 생성하는 API 입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "업체 생성 성공", content = @Content(schema = @Schema(implementation = ResDTO.class))),
            @ApiResponse(responseCode = "400", description = "업체 생성 실패.", content = @Content(schema = @Schema(implementation = ResDTO.class)))
    })
    @PostMapping("/api/company")
    ResponseEntity<ResDTO<ResCompanyDTO>> createCompany(@RequestBody ReqCompanyPostDTO dto);

    @Operation(summary = "업체 수정", description = "사용자 ID를 통해 업체를 수정하는 API 입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "업체 수정 성공", content = @Content(schema = @Schema(implementation = ResDTO.class))),
            @ApiResponse(responseCode = "400", description = "업체 수정 실패.", content = @Content(schema = @Schema(implementation = ResDTO.class)))
    })
    @PatchMapping("/api/company/{companyId}")
    ResponseEntity<ResDTO<ResCompanyDTO>> modifyCompany(@PathVariable Long companyId, @RequestBody ReqCompanyPostDTO dto);

    @Operation(summary = "업체 삭제", description = "사용자 ID를 통해 업체를 삭제하는 API 입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "업체 삭제 성공", content = @Content(schema = @Schema(implementation = ResDTO.class))),
            @ApiResponse(responseCode = "400", description = "업체 삭제 실패.", content = @Content(schema = @Schema(implementation = ResDTO.class)))
    })
    @DeleteMapping("/api/company/{companyId}")
    ResponseEntity<ResDTO<ResCompanyDTO>> deleteCompany(@PathVariable Long companyId);

}
