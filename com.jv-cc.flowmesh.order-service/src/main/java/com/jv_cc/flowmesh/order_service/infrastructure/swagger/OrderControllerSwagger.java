package com.jv_cc.flowmesh.order_service.infrastructure.swagger;

import com.jv_cc.flowmesh.order_service.application.dto.OrderDTO;
import com.jv_cc.flowmesh.order_service.presentation.request.OrderRequestPostDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Order", description = "주문 생성, 검색, 수정, 삭제 관련 사용자 API")
public interface OrderControllerSwagger {

    @Operation(summary = "주문 생성", description = "사용자 ID를 통해 주문을 생성하는 API 입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "주문 생성 성공", content = @Content(schema = @Schema(implementation = OrderDTO.class))),
            @ApiResponse(responseCode = "400", description = "주문 생성 실패.", content = @Content(schema = @Schema(implementation = OrderDTO.class)))
    })
    @PostMapping("/api/order")
    ResponseEntity<OrderDTO<Long>> createOrder(@RequestBody OrderRequestPostDTO dto);
}
