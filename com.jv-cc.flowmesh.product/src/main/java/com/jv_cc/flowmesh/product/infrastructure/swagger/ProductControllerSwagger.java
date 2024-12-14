package com.jv_cc.flowmesh.product.infrastructure.swagger;

import com.jv_cc.flowmesh.product.presentation.request.ReqProductPostDTO;
import com.jv_cc.flowmesh.product.presentation.response.ResDTO;
import com.jv_cc.flowmesh.product.presentation.response.ResProductDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Product", description = "상품 생성, 검색, 수정, 삭제 관련 사용자 API")
public interface ProductControllerSwagger {

    @Operation(summary = "상품 생성", description = "사용자 ID를 통해 상품을 생성하는 API 입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "상품 생성 성공", content = @Content(schema = @Schema(implementation = ResDTO.class))),
            @ApiResponse(responseCode = "400", description = "상품 생성 실패.", content = @Content(schema = @Schema(implementation = ResDTO.class)))
    })
    @PostMapping("/api/product")
    ResponseEntity<ResDTO<ResProductDTO>> createHub(@RequestBody ReqProductPostDTO dto);

}
