package com.jv_cc.flowmesh.product.infrastructure.swagger;

import com.jv_cc.flowmesh.product.presentation.request.ReqProductPostDTO;
import com.jv_cc.flowmesh.product.presentation.response.ResDTO;
import com.jv_cc.flowmesh.product.presentation.response.ResProductDTO;
import com.jv_cc.flowmesh.product.presentation.response.ResProductGetDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Product", description = "상품 생성, 검색, 수정, 삭제 관련 사용자 API")
public interface ProductControllerSwagger {

    @Operation(summary = "상품 생성", description = "사용자 ID를 통해 상품을 생성하는 API 입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "상품 생성 성공", content = @Content(schema = @Schema(implementation = ResDTO.class))),
            @ApiResponse(responseCode = "400", description = "상품 생성 실패.", content = @Content(schema = @Schema(implementation = ResDTO.class)))
    })
    @PostMapping("/api/product")
    ResponseEntity<ResDTO<ResProductDTO>> createProduct(@RequestBody ReqProductPostDTO dto);

    @Operation(summary = "상품 단건 조회", description = "상품 ID를 통해 상품을 단건 조회하는 API 입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "상품 단건 조회 성공", content = @Content(schema = @Schema(implementation = ResDTO.class))),
            @ApiResponse(responseCode = "400", description = "상품 단건 조회 실패.", content = @Content(schema = @Schema(implementation = ResDTO.class)))
    })
    @GetMapping("/api/product/{productId}")
    ResponseEntity<ResDTO<ResProductGetDTO>> getProduct(@PathVariable Long productId);

    @Operation(summary = "상품 수정", description = "사용자 ID를 통해 상품을 수정하는 API 입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "상품 수정 성공", content = @Content(schema = @Schema(implementation = ResDTO.class))),
            @ApiResponse(responseCode = "400", description = "상품 수정 실패.", content = @Content(schema = @Schema(implementation = ResDTO.class)))
    })
    @PatchMapping("/api/product/{productId}")
    ResponseEntity<ResDTO<ResProductDTO>> modifyProduct(@PathVariable Long productId, @RequestBody ReqProductPostDTO dto);

    @Operation(summary = "상품 삭제", description = "사용자 ID를 통해 상품을 삭제하는 API 입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "상품 삭제 성공", content = @Content(schema = @Schema(implementation = ResDTO.class))),
            @ApiResponse(responseCode = "400", description = "상품 삭제 실패.", content = @Content(schema = @Schema(implementation = ResDTO.class)))
    })
    @DeleteMapping("/api/product/{productId}")
    ResponseEntity<ResDTO<ResProductDTO>> deleteProduct(@PathVariable Long productId);
}
