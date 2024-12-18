package com.jv_cc.flowmesh.hub_server.presentation.controller;

import com.jv_cc.flowmesh.hub_server.application.service.HMIService;
import com.jv_cc.flowmesh.hub_server.presentation.request.ReqHMIPostDTO;
import com.jv_cc.flowmesh.hub_server.presentation.response.ResDTO;
import com.jv_cc.flowmesh.hub_server.presentation.response.ResHMIDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/hub-movement-infos")
public class HMIController {

    private final HMIService hmiService;

    @PostMapping
    public ResponseEntity<ResDTO<ResHMIDTO>> creatHMI(@RequestHeader("X-User-Id") String userId,
                                                      @RequestHeader("X-User-Role") String role,
                                                      @Valid @RequestBody ReqHMIPostDTO dto){
        return new ResponseEntity<>(
                ResDTO.<ResHMIDTO>builder()
                        .code(HttpStatus.CREATED.value())
                        .message("허브간 이동 정보가 생성되었습니다.")
                        .data(new ResHMIDTO(hmiService.createHMI(userId, role, dto).getHmiId()))
                        .build(),
                HttpStatus.CREATED
        );
    }

}
