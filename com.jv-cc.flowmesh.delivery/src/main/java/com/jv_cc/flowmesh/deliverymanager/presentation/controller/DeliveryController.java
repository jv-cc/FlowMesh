package com.jv_cc.flowmesh.deliverymanager.presentation.controller;

import com.jv_cc.flowmesh.deliverymanager.application.dto.DeliveryDTO;
import com.jv_cc.flowmesh.deliverymanager.application.service.DeliveryService;
import com.jv_cc.flowmesh.deliverymanager.presentation.request.DeliveryRequestPostDTO;
import com.jv_cc.flowmesh.deliverymanager.presentation.response.DeliveryResponseDTO;
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
@RequestMapping("/api/delivery")
public class DeliveryController {
    private final DeliveryService deliveryService;

    @PostMapping
    public ResponseEntity<ResponseDTO<DeliveryResponseDTO>> createDelivery(@Valid @RequestBody DeliveryRequestPostDTO dto){
        DeliveryDTO deliveryDTO = new DeliveryDTO(dto);

        deliveryDTO = deliveryService.createDelivery(deliveryDTO);

        DeliveryResponseDTO responseDTO = new DeliveryResponseDTO(deliveryDTO);

        return new ResponseEntity<>(ResponseDTO.<DeliveryResponseDTO>builder()
                .code(HttpStatus.CREATED.value())
                .message("배송이 접수되었습니다.")
                .data(responseDTO)
                .build(),
                HttpStatus.CREATED);


    }

    /**
     * TODO : RequestDTO 바꿔야 함, DeliveryResponseDTO 바꿔야 함
     */
    @PutMapping("/{deliveryId}")
    public ResponseEntity<ResponseDTO<DeliveryResponseDTO>> updateDelivery(@PathVariable Long deliveryId, @Valid @RequestBody DeliveryRequestPostDTO dto){
        DeliveryDTO deliveryDTO = new DeliveryDTO(dto);

        deliveryDTO = deliveryService.updateDelivery(deliveryDTO);

        DeliveryResponseDTO responseDTO = new DeliveryResponseDTO(deliveryDTO);

        return new ResponseEntity<>(ResponseDTO.<DeliveryResponseDTO>builder()
                .code(HttpStatus.OK.value())
                .message("배송 담당자가 수정되었습니다.")
                .data(responseDTO)
                .build(),
                HttpStatus.OK
        );
    }

    /**
     * TODO : RequestDTO 바꿔야 함, DeliveryResponseDTO 바꿔야 함
     */
    @PatchMapping("/{deliveryId}")
    public ResponseEntity<ResponseDTO<DeliveryResponseDTO>> modifyDelivery(@PathVariable Long deliveryId, @Valid @RequestBody DeliveryRequestPostDTO dto){
        DeliveryDTO deliveryDTO = new DeliveryDTO(dto);

        deliveryDTO = deliveryService.updateDelivery(deliveryDTO);

        DeliveryResponseDTO responseDTO = new DeliveryResponseDTO(deliveryDTO);

        return new ResponseEntity<>(ResponseDTO.<DeliveryResponseDTO>builder()
                .code(HttpStatus.OK.value())
                .message("배송 담당자가 수정되었습니다.")
                .data(responseDTO)
                .build(),
                HttpStatus.OK
        );

    }

    /**
     * TODO : DeliveryResponseDTO 바꿔야 함
     */
    @DeleteMapping("/{deliveryId}")
    public ResponseEntity<ResponseDTO<Long>> deleteDelivery(@PathVariable Long deliveryId){


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
    public ResponseEntity<ResponseDTO<DeliveryResponseDTO>> getDeliveries(){


        DeliveryDTO deliveryDTO = deliveryService.getDeliveries();

        DeliveryResponseDTO responseDTO = new DeliveryResponseDTO(deliveryDTO);

        return new ResponseEntity<>(ResponseDTO.<DeliveryResponseDTO>builder()
                .code(HttpStatus.OK.value())
                .message("배송 담당자를 조회했습니다.")
                .data(responseDTO)
                .build(),
                HttpStatus.OK);
    }

    /**
     * TODO : DeliveryManagerResponseDTO 바꿔야 함
     */
    @GetMapping("/{deliveryId}")
    public ResponseEntity<ResponseDTO<DeliveryResponseDTO>> getDelivery(@PathVariable Long deliveryId){


        DeliveryDTO deliveryDTO = deliveryService.getDelivery(deliveryId);

        DeliveryResponseDTO responseDTO = new DeliveryResponseDTO(deliveryDTO);

        return new ResponseEntity<>(ResponseDTO.<DeliveryResponseDTO>builder()
                .code(HttpStatus.OK.value())
                .message("배송 담당자를 조회했습니다.")
                .data(responseDTO)
                .build(),
                HttpStatus.OK);

    }

}
