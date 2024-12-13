package com.jv_cc.flowmesh.deliverymanager.presentation.controller;

import com.jv_cc.flowmesh.deliverymanager.application.dto.DeliveryManagerDTO;
import com.jv_cc.flowmesh.deliverymanager.application.service.DeliveryManagerService;
import com.jv_cc.flowmesh.deliverymanager.presentation.request.DeliveryManagerPostDTO;
import com.jv_cc.flowmesh.deliverymanager.presentation.response.DeliveryManagerResponseDTO;
import com.jv_cc.flowmesh.deliverymanager.presentation.response.ResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/delivery-manager")
public class DeliveryManagerController {
    private final DeliveryManagerService deliveryManagerService;

    @PostMapping
    public ResponseEntity<ResponseDTO<DeliveryManagerResponseDTO>> createDeliveryManager(@Valid @RequestBody DeliveryManagerPostDTO dto) {
        DeliveryManagerDTO deliveryManagerDTO = new DeliveryManagerDTO(dto);

        deliveryManagerDTO = deliveryManagerService.createDeliveryManager(deliveryManagerDTO);

        DeliveryManagerResponseDTO deliveryManagerResponseDTO = new DeliveryManagerResponseDTO(deliveryManagerDTO);

        return new ResponseEntity<>(ResponseDTO.<DeliveryManagerResponseDTO>builder()
                    .code(HttpStatus.CREATED.value())
                    .message("배송 담당자가 생성되었습니다.")
                    .data(deliveryManagerResponseDTO)
                    .build(),
                    HttpStatus.CREATED
                );
    }
    /**
     * TODO : RequestDTO 바꿔야 함, DeliveryManagerResponseDTO 바꿔야 함
     */
    @PutMapping("/{deliveryManagerId}")
    public ResponseEntity<ResponseDTO<DeliveryManagerResponseDTO>> updateDeliveryManager(@PathVariable Long deliveryManagerId, @Valid @RequestBody DeliveryManagerPostDTO dto) {
        DeliveryManagerDTO deliveryManagerDTO = new DeliveryManagerDTO(dto);

        deliveryManagerDTO = deliveryManagerService.updateDeliveryManager(deliveryManagerDTO);

        DeliveryManagerResponseDTO deliveryManagerResponseDTO = new DeliveryManagerResponseDTO(deliveryManagerDTO);

        return new ResponseEntity<>(ResponseDTO.<DeliveryManagerResponseDTO>builder()
                .code(HttpStatus.OK.value())
                .message("배송 담당자가 수정되었습니다.")
                .data(deliveryManagerResponseDTO)
                .build(),
                HttpStatus.OK
        );
    }

    /**
     * TODO : DeliveryManagerResponseDTO 바꿔야 함
     */
    @DeleteMapping("/{deliveryManagerId}")
    public ResponseEntity<ResponseDTO<Long>> deleteDeliveryManager(@PathVariable Long deliveryManagerId) {


        Long responseId = deliveryManagerService.deleteDeliveryManager(deliveryManagerId);



        return new ResponseEntity<>(ResponseDTO.<Long>builder()
                .code(HttpStatus.OK.value())
                .message("배송 담당자가 삭제되었습니다.")
                .data(responseId)
                .build(),
                HttpStatus.OK);

    }

    /**
     * TODO : Paging처리 해야 함, DeliveryManagerResponseDTO 바꿔야 함
     */
    @GetMapping
    public ResponseEntity<ResponseDTO<DeliveryManagerResponseDTO>> getDeliveryManager() {


        DeliveryManagerDTO deliveryManagerDTO = deliveryManagerService.getOrders();

        DeliveryManagerResponseDTO deliveryManagerResponseDTO = new DeliveryManagerResponseDTO(deliveryManagerDTO);

        return new ResponseEntity<>(ResponseDTO.<DeliveryManagerResponseDTO>builder()
                .code(HttpStatus.OK.value())
                .message("배송 담당자를 조회했습니다.")
                .data(deliveryManagerResponseDTO)
                .build(),
                HttpStatus.OK);
    }

    /**
     * TODO : DeliveryManagerResponseDTO 바꿔야 함
     */
    @GetMapping("/{deliveryManagerId}")
    public ResponseEntity<ResponseDTO<DeliveryManagerResponseDTO>> getDeliveryManagerById(@PathVariable Long deliveryManagerId) {


        DeliveryManagerDTO deliveryManagerDTO = deliveryManagerService.getOrder(deliveryManagerId);

        DeliveryManagerResponseDTO deliveryManagerResponseDTO = new DeliveryManagerResponseDTO(deliveryManagerDTO);

        return new ResponseEntity<>(ResponseDTO.<DeliveryManagerResponseDTO>builder()
                .code(HttpStatus.OK.value())
                .message("배송 담당자를 조회했습니다.")
                .data(deliveryManagerResponseDTO)
                .build(),
                HttpStatus.OK);
    }

}
