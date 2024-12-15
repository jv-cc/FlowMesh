package com.jv_cc.flowmesh.delivery.presentation.controller;

import com.jv_cc.flowmesh.delivery.application.dto.DeliveryCreateDTO;
import com.jv_cc.flowmesh.delivery.application.dto.DeliveryDTO;
import com.jv_cc.flowmesh.delivery.domain.service.DeliveryService;
import com.jv_cc.flowmesh.delivery.presentation.request.DeliveryPostDTO;
import com.jv_cc.flowmesh.delivery.presentation.response.DeliveryPostResponseDTO;
import com.jv_cc.flowmesh.delivery.presentation.response.DeliveryResponseDTO;
import com.jv_cc.flowmesh.delivery.presentation.response.ResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/delivery")
public class DeliveryController {
    private final DeliveryService deliveryService;

    @PostMapping
    public ResponseEntity<ResponseDTO<DeliveryPostResponseDTO>> createDelivery(@Valid @RequestBody DeliveryPostDTO dto) {
        DeliveryCreateDTO deliveryCreateDTO = new DeliveryCreateDTO(dto);

        deliveryCreateDTO = deliveryService.createDelivery(deliveryCreateDTO);

        DeliveryPostResponseDTO deliveryPostResponseDTO = deliveryCreateDTO.toResponseDTO();

        return new ResponseEntity<>(ResponseDTO.<DeliveryPostResponseDTO>builder()
                    .code(HttpStatus.CREATED.value())
                    .message("배송 담당자가 생성되었습니다.")
                    .data(deliveryPostResponseDTO)
                    .build(),
                    HttpStatus.CREATED
                );
    }
    /**
     * TODO : RequestDTO 바꿔야 함, DeliveryManagerResponseDTO 바꿔야 함
     */
    @PutMapping("/{deliveryId}")
    public ResponseEntity<ResponseDTO<DeliveryResponseDTO>> updateDelivery(@PathVariable Long deliveryId, @Valid @RequestBody DeliveryPostDTO dto) {
        DeliveryDTO deliveryDTO = new DeliveryDTO(dto);

        deliveryDTO = deliveryService.updateDelivery(deliveryDTO);

        DeliveryResponseDTO deliveryResponseDTO = new DeliveryResponseDTO(deliveryDTO);

        return new ResponseEntity<>(ResponseDTO.<DeliveryResponseDTO>builder()
                .code(HttpStatus.OK.value())
                .message("배송 담당자가 수정되었습니다.")
                .data(deliveryResponseDTO)
                .build(),
                HttpStatus.OK
        );
    }

    /**
     * TODO : DeliveryManagerResponseDTO 바꿔야 함
     */
    @DeleteMapping("/{deliveryId}")
    public ResponseEntity<ResponseDTO<Long>> deleteDelivery(@PathVariable Long deliveryId) {


        Long responseId = deliveryService.deleteDelivery(deliveryId);



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
    public ResponseEntity<ResponseDTO<DeliveryResponseDTO>> getDelivery() {


        DeliveryDTO deliveryDTO = deliveryService.getOrders();

        DeliveryResponseDTO deliveryResponseDTO = new DeliveryResponseDTO(deliveryDTO);

        return new ResponseEntity<>(ResponseDTO.<DeliveryResponseDTO>builder()
                .code(HttpStatus.OK.value())
                .message("배송 담당자를 조회했습니다.")
                .data(deliveryResponseDTO)
                .build(),
                HttpStatus.OK);
    }

    /**
     * TODO : DeliveryManagerResponseDTO 바꿔야 함
     */
    @GetMapping("/{deliveryId}")
    public ResponseEntity<ResponseDTO<DeliveryResponseDTO>> getDeliveryById(@PathVariable Long deliveryId) {


        DeliveryDTO deliveryDTO = deliveryService.getOrder(deliveryId);

        DeliveryResponseDTO deliveryResponseDTO = new DeliveryResponseDTO(deliveryDTO);

        return new ResponseEntity<>(ResponseDTO.<DeliveryResponseDTO>builder()
                .code(HttpStatus.OK.value())
                .message("배송 담당자를 조회했습니다.")
                .data(deliveryResponseDTO)
                .build(),
                HttpStatus.OK);
    }

}
