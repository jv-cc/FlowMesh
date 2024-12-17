package com.jv_cc.flowmesh.delivery.infrastructure.swagger;

import com.jv_cc.flowmesh.delivery.presentation.request.DeliveryPostDTO;
import com.jv_cc.flowmesh.delivery.presentation.response.*;
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

@Tag(name = "Delivery", description = "배송 관련 API")
public interface DeliverySwagger {

    @Operation(summary = "배송 생성", description = "배송을 생성하는 API 입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "배송 생성 성공", content = @Content(schema = @Schema(implementation = ResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "배송 생성 실패.", content = @Content(schema = @Schema(implementation = ResponseDTO.class)))
    })
    @PostMapping("/api/deliveries")
    ResponseEntity<ResponseDTO<DeliveryPostResponseDTO>> createDelivery(
            @RequestHeader("X-User-Id") String userId,
            @RequestHeader("X-User-Role") String role,
            @Valid @RequestBody DeliveryPostDTO dto
    );


    @Operation(summary = "배송 수정", description = "사용자 ID를 통해 배송을 수정하는 API 입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "허브 수정 성공", content = @Content(schema = @Schema(implementation = ResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "허브 수정 실패.", content = @Content(schema = @Schema(implementation = ResponseDTO.class)))
    })
    @PutMapping("/api/deliveries/{deliveryId}")
    ResponseEntity<ResponseDTO<DeliveryResponseDTO>> updateDelivery(
            @RequestHeader("X-User-Id") String userId,
            @RequestHeader("X-User-Role") String role,
            @PathVariable Long deliveryId,
            @Valid @RequestBody DeliveryPostDTO dto
    );


    @Operation(summary = "배송 삭제", description = "사용자 ID를 통해 배송을 삭제하는 API 입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "배송 삭제 성공", content = @Content(schema = @Schema(implementation = ResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "배송 삭제 실패.", content = @Content(schema = @Schema(implementation = ResponseDTO.class)))
    })
    @DeleteMapping("/api/deliveries/{deliveryId}")
    ResponseEntity<ResponseDTO<DeliveryDeleteResponseDTO>> deleteDelivery(
            @RequestHeader("X-User-Id") String userId,
            @RequestHeader("X-User-Role") String role,
            @PathVariable Long deliveryId
    );


    @Operation(summary = "배송 조회", description = "배송을 조회하는 API 입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "배송 조회 성공", content = @Content(schema = @Schema(implementation = ResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "배송 조회 실패.", content = @Content(schema = @Schema(implementation = ResponseDTO.class)))
    })
    @GetMapping("/api/deliveries")
    ResponseEntity<ResponseDTO<Page<DeliveryAllGetResponseDTO>>> getDeliveries(
            @RequestHeader("X-User-Id") String userId,
            @RequestHeader("X-User-Role") String role,
            @RequestParam(name = "order_id") Long orderId,
            @PageableDefault(size = 10,
                    sort={"order_id, delivery_sequence"},
                    direction = Sort.Direction.DESC
            ) Pageable pageable
    );


    @Operation(summary = "배송 조회", description = "사용자 ID를 통해 배송을 조회하는 API 입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "배송 조회 성공", content = @Content(schema = @Schema(implementation = ResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "배송 조회 실패.", content = @Content(schema = @Schema(implementation = ResponseDTO.class)))
    })
    @GetMapping("/api/deliveries/{deliveryId}")
    ResponseEntity<ResponseDTO<DeliveryGetResponseDTO>> getDeliveryById(
            @RequestHeader("X-User-Id") String userId,
            @RequestHeader("X-User-Role") String role,
            @PathVariable Long deliveryId
    );


}
