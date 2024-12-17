package com.jv_cc.flowmesh.order_service.infrastructure.swagger;

import com.jv_cc.flowmesh.order_service.application.dto.OrderDTO;
import com.jv_cc.flowmesh.order_service.presentation.request.OrderRequestPostDTO;
import com.jv_cc.flowmesh.order_service.presentation.request.OrderRequestPutDTO;
import com.jv_cc.flowmesh.order_service.presentation.response.*;
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

@Tag(name = "Order", description = "주문 생성, 검색, 수정, 삭제 관련 사용자 API")
public interface OrderControllerSwagger {

    @Operation(summary = "주문 생성", description = "사용자 ID를 통해 주문을 생성하는 API 입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "주문 생성 성공", content = @Content(schema = @Schema(implementation = ResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "주문 생성 실패.", content = @Content(schema = @Schema(implementation = ResponseDTO.class)))
    })
    @PostMapping("/api/order")
    ResponseEntity<ResponseDTO<OrderPostResponseDTO>> createOrder(
            @RequestHeader("X-User-Id") String userId,
            @RequestHeader("X-User-Role") String role,
            @Valid @RequestBody OrderRequestPostDTO dto
    );


    @Operation(summary = "주문 수정", description = "주문을 수정하는 API 입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "주문 수정 성공", content = @Content(schema = @Schema(implementation = ResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "주문 수정 실패.", content = @Content(schema = @Schema(implementation = ResponseDTO.class)))
    })
    @PutMapping("/api/order/{orderId}")
    ResponseEntity<ResponseDTO<OrderPutResponseDTO>> updateOrder(
            @RequestHeader("X-User-Id") String userId,
            @RequestHeader("X-User-Role") String role,
            @PathVariable Long orderId,
            @Valid @RequestBody OrderRequestPutDTO dto
    );


    @Operation(summary = "주문 삭제", description = "주문을 삭제하는 API 입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "주문 삭제 성공", content = @Content(schema = @Schema(implementation = ResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "주문 삭제 실패.", content = @Content(schema = @Schema(implementation = ResponseDTO.class)))
    })
    @DeleteMapping("/api/order/{orderId}")
    ResponseEntity<ResponseDTO<Map<String, Object>>> deleteOrder(
            @RequestHeader("X-User-Id") String userId,
            @RequestHeader("X-User-Role") String role,
            @PathVariable Long orderId
    );


    @Operation(summary = "주문 전체 조회", description = "주문을 전체 조회하는 API 입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "주문 조회 성공", content = @Content(schema = @Schema(implementation = ResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "주문 조회 실패.", content = @Content(schema = @Schema(implementation = ResponseDTO.class)))
    })
    @GetMapping("/api/order")
    ResponseEntity<ResponseDTO<Page<OrderGetResponseDTO>>> getOrders(
            @RequestHeader("X-User-Id") String userId,
            @RequestHeader("X-User-Role") String role,
            @PageableDefault(size = 10,
                    sort = {"createdAt","updatedAt"},
                    direction = Sort.Direction.DESC) Pageable pageable
    );


    @Operation(summary = "주문 단건 조회", description = "주문을 단건 조회하는 API 입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "주문 조회 성공", content = @Content(schema = @Schema(implementation = ResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "주문 조회 실패.", content = @Content(schema = @Schema(implementation = ResponseDTO.class)))
    })
    @GetMapping("/api/order/{orderId}")
    ResponseEntity<ResponseDTO<OrderGetOneResponseDTO>> getOrder(
            @RequestHeader("X-User-Id") String userId,
            @RequestHeader("X-User-Role") String role,
            @PathVariable Long orderId
    );
}
