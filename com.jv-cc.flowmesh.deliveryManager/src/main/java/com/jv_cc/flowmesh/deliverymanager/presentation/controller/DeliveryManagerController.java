package com.jv_cc.flowmesh.deliverymanager.presentation.controller;

import com.jv_cc.flowmesh.deliverymanager.application.dto.DeliveryManagerCreateDTO;
import com.jv_cc.flowmesh.deliverymanager.application.dto.DeliveryManagerDTO;
import com.jv_cc.flowmesh.deliverymanager.application.dto.DeliveryManagerPutDTO;
import com.jv_cc.flowmesh.deliverymanager.domain.service.DeliveryManagerService;
import com.jv_cc.flowmesh.deliverymanager.presentation.request.DeliveryManagerPostRequestDTO;
import com.jv_cc.flowmesh.deliverymanager.presentation.request.DeliveryManagerPutRequestDTO;
import com.jv_cc.flowmesh.deliverymanager.presentation.response.*;
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

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/delivery-managers")
public class DeliveryManagerController {
    private final DeliveryManagerService deliveryManagerService;

    @PostMapping
    public ResponseEntity<ResponseDTO<DeliveryManagerPostResponseDTO>> createDeliveryManager(
            @RequestHeader("X-User-Id") String userId,
            @RequestHeader("X-User-Role") String role,
            @Valid @RequestBody DeliveryManagerPostRequestDTO dto
    ) {
        DeliveryManagerCreateDTO deliveryManagerCreateDTO = new DeliveryManagerCreateDTO(dto);

        deliveryManagerCreateDTO = deliveryManagerService.createDeliveryManager(deliveryManagerCreateDTO);

        DeliveryManagerPostResponseDTO deliveryManagerResponseDTO = deliveryManagerCreateDTO.toResponseDTO();

        return new ResponseEntity<>(ResponseDTO.<DeliveryManagerPostResponseDTO>builder()
                    .code(HttpStatus.CREATED.value())
                    .message("배송 담당자가 생성되었습니다.")
                    .data(deliveryManagerResponseDTO)
                    .build(),
                    HttpStatus.CREATED
                );
    }

    @PutMapping("/{deliveryManagerId}")
    public ResponseEntity<ResponseDTO<DeliveryManagerPutResponseDTO>> updateDeliveryManager(
            @RequestHeader("X-User-Id") String userId,
            @RequestHeader("X-User-Role") String role,
            @PathVariable Long deliveryManagerId,
            @Valid @RequestBody DeliveryManagerPutRequestDTO dto
    ) {
        DeliveryManagerPutDTO deliveryManagerDTO = new DeliveryManagerPutDTO(dto, deliveryManagerId);

        deliveryManagerDTO = deliveryManagerService.updateDeliveryManager(deliveryManagerDTO);

        DeliveryManagerPutResponseDTO deliveryManagerResponseDTO = deliveryManagerDTO.toResponsePutDTO();

        return new ResponseEntity<>(ResponseDTO.<DeliveryManagerPutResponseDTO>builder()
                .code(HttpStatus.OK.value())
                .message("배송 담당자가 수정되었습니다.")
                .data(deliveryManagerResponseDTO)
                .build(),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{deliveryManagerId}")
    public ResponseEntity<ResponseDTO<Map<String, Object>>> deleteDeliveryManager(
            @RequestHeader("X-User-Id") String userId,
            @RequestHeader("X-User-Role") String role,
            @PathVariable Long deliveryManagerId
    ) {
        Map<String, Object> response = deliveryManagerService.deleteDeliveryManager(deliveryManagerId);

        return new ResponseEntity<>(ResponseDTO.<Map<String, Object>>builder()
                .code(HttpStatus.OK.value())
                .message("배송 담당자가 삭제되었습니다.")
                .data(response)
                .build(),
                HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ResponseDTO<Page<DeliveryManagerGetOneResponseDTO>>> getDeliveryManager(
            @RequestHeader("X-User-Id") String userId,
            @RequestHeader("X-User-Role") String role,
            @PageableDefault(size = 10,
            sort = {"deliveryManagerId"},
            direction = Sort.Direction.DESC ) Pageable pageable
    ) {

        Page<DeliveryManagerGetOneResponseDTO> deliveryManagerResponseDTO = deliveryManagerService.getOrders(pageable);

        return new ResponseEntity<>(ResponseDTO.<Page<DeliveryManagerGetOneResponseDTO>>builder()
                .code(HttpStatus.OK.value())
                .message("배송 담당자를 조회했습니다.")
                .data(deliveryManagerResponseDTO)
                .build(),
                HttpStatus.OK);
    }

    @GetMapping("/{deliveryManagerId}")
    public ResponseEntity<ResponseDTO<DeliveryManagerGetOneResponseDTO>> getDeliveryManagerById(
            @RequestHeader("X-User-Id") String userId,
            @RequestHeader("X-User-Role") String role,
            @PathVariable Long deliveryManagerId
    ) {

        DeliveryManagerGetOneResponseDTO deliveryManagerResponseDTO = deliveryManagerService.getOrder(deliveryManagerId);

        return new ResponseEntity<>(ResponseDTO.<DeliveryManagerGetOneResponseDTO>builder()
                .code(HttpStatus.OK.value())
                .message("배송 담당자를 조회했습니다.")
                .data(deliveryManagerResponseDTO)
                .build(),
                HttpStatus.OK);
    }

}
