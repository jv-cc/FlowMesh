package com.jv_cc.flowmesh.deliverymanager.infrastructure.swagger;

import com.jv_cc.flowmesh.deliverymanager.presentation.request.DeliveryManagerPostRequestDTO;
import com.jv_cc.flowmesh.deliverymanager.presentation.request.DeliveryManagerPutRequestDTO;
import com.jv_cc.flowmesh.deliverymanager.presentation.response.DeliveryManagerGetOneResponseDTO;
import com.jv_cc.flowmesh.deliverymanager.presentation.response.DeliveryManagerPostResponseDTO;
import com.jv_cc.flowmesh.deliverymanager.presentation.response.DeliveryManagerPutResponseDTO;
import com.jv_cc.flowmesh.deliverymanager.presentation.response.ResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Tag(name = "DeliveryManager", description = "배송 담당자 관련 API")
public interface DeliveryManagerSwagger {

    @Operation(summary = "배송 담당자 생성", description = "사용자 ID를 통해 배송 담당자를 생성하는 API 입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "배송 담당자 생성 성공", content = @Content(schema = @Schema(implementation = ResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "배송 담당자 생성 실패.", content = @Content(schema = @Schema(implementation = ResponseDTO.class)))
    })
    @PostMapping("/api/delivery-managers")
    ResponseEntity<ResponseDTO<DeliveryManagerPostResponseDTO>> createDeliveryManager(
            @RequestHeader("X-User-Id") String userId,
            @RequestHeader("X-User-Role") String role,
            @Valid @RequestBody DeliveryManagerPostRequestDTO dto
    );


    @Operation(summary = "배송 담당자 수정", description = "ID를 통해 배송 담당자를 수정하는 API 입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "배송 담당자 수정 성공", content = @Content(schema = @Schema(implementation = ResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "배송 담당자 수정 실패.", content = @Content(schema = @Schema(implementation = ResponseDTO.class)))
    })
    @PutMapping("/api/delivery-managers/{deliveryManagerId}")
    ResponseEntity<ResponseDTO<DeliveryManagerPutResponseDTO>> updateDeliveryManager(
            @RequestHeader("X-User-Id") String userId,
            @RequestHeader("X-User-Role") String role,
            @PathVariable Long deliveryManagerId,
            @Valid @RequestBody DeliveryManagerPutRequestDTO dto
    );


    @Operation(summary = "배송 담당자 삭제", description = "ID를 통해 배송 담당자를 삭제하는 API 입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "배송 담당자 삭제 성공", content = @Content(schema = @Schema(implementation = ResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "배송 담당자 삭제 실패.", content = @Content(schema = @Schema(implementation = ResponseDTO.class)))
    })
    @DeleteMapping("/api/delivery-managers/{deliveryManagerId}")
    ResponseEntity<ResponseDTO<Map<String, Object>>> deleteDeliveryManager(
            @RequestHeader("X-User-Id") String userId,
            @RequestHeader("X-User-Role") String role,
            @PathVariable Long deliveryManagerId
    );


    @Operation(summary = "배송 담당자 조회", description = "배송 담당자를 전체 조회하는 API 입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "배송 담당자 조회 성공", content = @Content(schema = @Schema(implementation = ResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "배송 담당자 조회 실패.", content = @Content(schema = @Schema(implementation = ResponseDTO.class)))
    })
    @GetMapping("/api/delivery-managers")
    ResponseEntity<ResponseDTO<Page<DeliveryManagerGetOneResponseDTO>>> getDeliveryManager(
            @RequestHeader("X-User-Id") String userId,
            @RequestHeader("X-User-Role") String role,
            @PageableDefault(size = 10,
                    sort = {"deliveryManagerId"},
                    direction = Sort.Direction.DESC ) Pageable pageable
    );


    @Operation(summary = "배송 담당자 조회", description = "ID를 통해 배송 담당자를 조회하는 API 입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "배송 담당자 조회 성공", content = @Content(schema = @Schema(implementation = ResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "배송 담당자 조회 실패.", content = @Content(schema = @Schema(implementation = ResponseDTO.class)))
    })
    @GetMapping("/api/delivery-managers/{deliveryManagerId}")
    ResponseEntity<ResponseDTO<DeliveryManagerGetOneResponseDTO>> getDeliveryManagerById(
            @RequestHeader("X-User-Id") String userId,
            @RequestHeader("X-User-Role") String role,
            @PathVariable Long deliveryManagerId
    );

}
