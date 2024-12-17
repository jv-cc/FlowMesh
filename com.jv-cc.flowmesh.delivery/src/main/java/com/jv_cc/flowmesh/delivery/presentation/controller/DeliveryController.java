package com.jv_cc.flowmesh.delivery.presentation.controller;

import com.jv_cc.flowmesh.delivery.application.dto.DeliveryCreateDTO;
import com.jv_cc.flowmesh.delivery.application.dto.DeliveryDTO;
import com.jv_cc.flowmesh.delivery.application.dto.DeliveryDeleteDTO;
import com.jv_cc.flowmesh.delivery.domain.service.DeliveryService;
import com.jv_cc.flowmesh.delivery.presentation.request.DeliveryPostDTO;
import com.jv_cc.flowmesh.delivery.presentation.response.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/deliveries")
public class DeliveryController {
    private final DeliveryService deliveryService;

    @PostMapping
    public ResponseEntity<ResponseDTO<DeliveryPostResponseDTO>> createDelivery(
            @RequestHeader("X-User-Id") String userId,
            @RequestHeader("X-User-Role") String role,
            @Valid @RequestBody DeliveryPostDTO dto
    ) {
        DeliveryCreateDTO deliveryCreateDTO = new DeliveryCreateDTO(dto);

        deliveryCreateDTO = deliveryService.createDelivery(deliveryCreateDTO);

        DeliveryPostResponseDTO deliveryPostResponseDTO = deliveryCreateDTO.toResponseDTO();

        return new ResponseEntity<>(ResponseDTO.<DeliveryPostResponseDTO>builder()
                    .code(HttpStatus.CREATED.value())
                    .message("배송이 생성되었습니다.")
                    .data(deliveryPostResponseDTO)
                    .build(),
                    HttpStatus.CREATED
                );
    }

    /**
     * TODO : RequestDTO 바꿔야 함, DeliveryManagerResponseDTO 바꿔야 함
     */
    @PutMapping("/{deliveryId}")
    public ResponseEntity<ResponseDTO<DeliveryResponseDTO>> updateDelivery(
            @RequestHeader("X-User-Id") String userId,
            @RequestHeader("X-User-Role") String role,
            @PathVariable Long deliveryId,
            @Valid @RequestBody DeliveryPostDTO dto
    ) {
        DeliveryDTO deliveryDTO = new DeliveryDTO(dto);

        deliveryDTO = deliveryService.updateDelivery(deliveryDTO);

        DeliveryResponseDTO deliveryResponseDTO = new DeliveryResponseDTO(deliveryDTO);

        return new ResponseEntity<>(ResponseDTO.<DeliveryResponseDTO>builder()
                .code(HttpStatus.OK.value())
                .message("배송이 수정되었습니다.")
                .data(deliveryResponseDTO)
                .build(),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{deliveryId}")
    public ResponseEntity<ResponseDTO<DeliveryDeleteResponseDTO>> deleteDelivery(
            @RequestHeader("X-User-Id") String userId,
            @RequestHeader("X-User-Role") String role,
            @PathVariable Long deliveryId
    ) {

        DeliveryDeleteResponseDTO responseId = deliveryService.deleteDelivery(deliveryId).toResponseDTO();

        return new ResponseEntity<>(ResponseDTO.<DeliveryDeleteResponseDTO>builder()
                .code(HttpStatus.OK.value())
                .message("배송을 삭제했습니다.")
                .data(responseId)
                .build(),
                HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity<ResponseDTO<Page<DeliveryAllGetResponseDTO>>> getDeliveries(
            @RequestHeader("X-User-Id") String userId,
            @RequestHeader("X-User-Role") String role,
            @RequestParam(name = "order_id") Long orderId,
            @PageableDefault(size = 10,
                    sort={"order_id, delivery_sequence"},
                    direction = Sort.Direction.DESC
            ) Pageable pageable
    ) {
        Page<DeliveryAllGetResponseDTO> deliveryDTOs = deliveryService.getDeliveries(orderId, pageable);

        return new ResponseEntity<>(ResponseDTO.<Page<DeliveryAllGetResponseDTO>>builder()
                .code(HttpStatus.OK.value())
                .message("배송을 조회했습니다.")
                .data(deliveryDTOs)
                .build(),
                HttpStatus.OK);
    }

    @GetMapping("/{deliveryId}")
    public ResponseEntity<ResponseDTO<DeliveryGetResponseDTO>> getDeliveryById(
            @RequestHeader("X-User-Id") String userId,
            @RequestHeader("X-User-Role") String role,
            @PathVariable Long deliveryId
    ) {

        DeliveryGetResponseDTO deliveryDTO = deliveryService.getDeliveryById(deliveryId);

        return new ResponseEntity<>(ResponseDTO.<DeliveryGetResponseDTO>builder()
                .code(HttpStatus.OK.value())
                .message("배송을 조회했습니다.")
                .data(deliveryDTO)
                .build(),
                HttpStatus.OK);
    }

}
