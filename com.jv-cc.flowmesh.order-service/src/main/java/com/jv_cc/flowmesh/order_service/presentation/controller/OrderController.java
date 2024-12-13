package com.jv_cc.flowmesh.order_service.presentation.controller;

import com.jv_cc.flowmesh.order_service.application.dto.OrderDTO;
import com.jv_cc.flowmesh.order_service.application.service.OrderService;
import com.jv_cc.flowmesh.order_service.presentation.request.OrderRequestPostDTO;
import com.jv_cc.flowmesh.order_service.presentation.request.OrderRequestPutDTO;
import com.jv_cc.flowmesh.order_service.presentation.response.ResponseDTO;
import com.jv_cc.flowmesh.order_service.presentation.response.OrderResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<ResponseDTO<OrderResponseDTO>> createOrder(@Valid @RequestBody OrderRequestPostDTO dto){
        OrderDTO orderDto = new OrderDTO(dto);

        orderDto = orderService.createOrder(orderDto);

        OrderResponseDTO orderResponseDto = new OrderResponseDTO(orderDto);

        return new ResponseEntity<>(ResponseDTO.<OrderResponseDTO>builder()
                .code(HttpStatus.CREATED.value())
                .message("주문이 접수되었습니다.")
                .data(orderResponseDto)
                .build(),
            HttpStatus.CREATED
        );
    }

    /**
     * TODO : RequestDTO 바꿔야 함, OrderResponseDTO 바꿔야 함
     */
    @PutMapping("/{orderId}")
    public ResponseEntity<ResponseDTO> updateOrder(@PathVariable Long orderId, @Valid @RequestBody OrderRequestPostDTO dto){
        OrderDTO orderDto = new OrderDTO(dto);

        orderDto = orderService.updateOrder(orderDto);

        OrderResponseDTO orderResponseDto = new OrderResponseDTO(orderDto);

        return new ResponseEntity<>(ResponseDTO.builder()
                .code(HttpStatus.OK.value())
                .message("주문이 수정되었습니다.")
                .build(),
                HttpStatus.OK
        );
    }

    /**
     * TODO : OrderResponseDTO 바꿔야 함
     */
    @DeleteMapping("/{orderId}")
    public ResponseEntity<ResponseDTO> deleteOrder(@PathVariable Long orderId){


        Long responseId = orderService.deleteOrder(orderId);



        return new ResponseEntity<>(ResponseDTO.<Long>builder()
                .code(HttpStatus.OK.value())
                .message("주문이 삭제되었습니다.")
                .data(responseId)
                .build(),
                HttpStatus.OK
        );
    }

    /**
     * TODO : Paging처리 해야 함, OrderResponseDTO 바꿔야 함
     */
    @GetMapping
    public ResponseEntity<ResponseDTO<OrderResponseDTO>> getOrders(){


        OrderDTO orderDTO = orderService.getOrders();

        OrderResponseDTO orderResponseDto = new OrderResponseDTO(orderDTO);

        return new ResponseEntity<>(ResponseDTO.<OrderResponseDTO>builder()
                .code(HttpStatus.OK.value())
                .message("주문을 조회했습니다.")
                .data(orderResponseDto)
                .build(),
                HttpStatus.OK
        );

    }

    /**
     * TODO : OrderResponseDTO 바꿔야 함
     */
    @GetMapping("/{orderId}")
    public ResponseEntity<ResponseDTO<OrderResponseDTO>> getOrder(@PathVariable Long orderId){


        OrderDTO orderDTO = orderService.getOrder(orderId);

        OrderResponseDTO orderResponseDto = new OrderResponseDTO(orderDTO);

        return new ResponseEntity<>(ResponseDTO.<OrderResponseDTO>builder()
                .code(HttpStatus.OK.value())
                .message("주문을 조회했습니다.")
                .data(orderResponseDto)
                .build(),
                HttpStatus.OK
        );

    }
}
