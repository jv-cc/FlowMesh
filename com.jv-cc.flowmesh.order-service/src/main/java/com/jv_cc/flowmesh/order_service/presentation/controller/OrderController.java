package com.jv_cc.flowmesh.order_service.presentation.controller;

import com.jv_cc.flowmesh.order_service.application.dto.OrderCreateDTO;
import com.jv_cc.flowmesh.order_service.application.dto.OrderPutDTO;
import com.jv_cc.flowmesh.order_service.domain.service.OrderService;
import com.jv_cc.flowmesh.order_service.presentation.request.OrderRequestPostDTO;
import com.jv_cc.flowmesh.order_service.presentation.request.OrderRequestPutDTO;
import com.jv_cc.flowmesh.order_service.presentation.response.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<ResponseDTO<OrderPostResponseDTO>> createOrder(
            @RequestHeader("X-User-Id") String userId,
            @RequestHeader("X-User-Role") String role,
            @Valid @RequestBody OrderRequestPostDTO dto
    ){
        OrderCreateDTO orderCreateDto = new OrderCreateDTO(dto);

        orderCreateDto = orderService.createOrder(orderCreateDto);

        OrderPostResponseDTO orderResponseDto = orderCreateDto.toResponsePostDTO();

        return new ResponseEntity<>(ResponseDTO.<OrderPostResponseDTO>builder()
                .code(HttpStatus.CREATED.value())
                .message("주문이 접수되었습니다.")
                .data(orderResponseDto)
                .build(),
            HttpStatus.CREATED
        );
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<ResponseDTO<OrderPutResponseDTO>> updateOrder(
            @RequestHeader("X-User-Id") String userId,
            @RequestHeader("X-User-Role") String role,
            @PathVariable Long orderId,
            @Valid @RequestBody OrderRequestPutDTO dto
    ){
        OrderPutDTO orderDto = new OrderPutDTO(dto, orderId);

        orderDto = orderService.updateOrder(orderDto);

        OrderPutResponseDTO orderResponseDto = orderDto.toResponse();

        return new ResponseEntity<>(ResponseDTO.<OrderPutResponseDTO>builder()
                .code(HttpStatus.OK.value())
                .message("주문이 수정되었습니다.")
                .data(orderResponseDto)
                .build(),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<ResponseDTO<Map<String, Object>>> deleteOrder(
            @RequestHeader("X-User-Id") String userId,
            @RequestHeader("X-User-Role") String role,
            @PathVariable Long orderId
    ){
        Map<String, Object> result = orderService.deleteOrder(orderId);



        return new ResponseEntity<>(ResponseDTO.<Map<String, Object>>builder()
                .code(HttpStatus.OK.value())
                .message("주문이 삭제되었습니다.")
                .data(result)
                .build(),
                HttpStatus.OK
        );
    }

    /**
     * TODO : Paging처리 해야 함, OrderResponseDTO 바꿔야 함
     */
    @GetMapping
    public ResponseEntity<ResponseDTO<Page<OrderGetResponseDTO>>> getOrders(
            @RequestHeader("X-User-Id") String userId,
            @RequestHeader("X-User-Role") String role,
            @PageableDefault(size = 10,
                    sort = {"createdAt","updatedAt"},
                    direction = Sort.Direction.DESC) Pageable pageable
    ){
        Page<OrderGetResponseDTO> orderDTOs = orderService.getOrders(pageable);

        return new ResponseEntity<>(ResponseDTO.<Page<OrderGetResponseDTO>>builder()
                .code(HttpStatus.OK.value())
                .message("주문을 조회했습니다.")
                .data(orderDTOs)
                .build(),
                HttpStatus.OK
        );

    }

    @GetMapping("/{orderId}")
    public ResponseEntity<ResponseDTO<OrderGetOneResponseDTO>> getOrder(
            @RequestHeader("X-User-Id") String userId,
            @RequestHeader("X-User-Role") String role,
            @PathVariable Long orderId
    ){
        OrderGetOneResponseDTO orderResponseDto = orderService.getOrder(orderId);

        return new ResponseEntity<>(ResponseDTO.<OrderGetOneResponseDTO>builder()
                .code(HttpStatus.OK.value())
                .message("주문을 조회했습니다.")
                .data(orderResponseDto)
                .build(),
                HttpStatus.OK
        );

    }
}
