package com.jv_cc.flowmesh.hub_server.presentation.controller;

import com.jv_cc.flowmesh.hub_server.application.service.HubService;
import com.jv_cc.flowmesh.hub_server.infrastructure.swagger.HubControllerSwagger;
import com.jv_cc.flowmesh.hub_server.presentation.request.ReqHubPostDTO;
import com.jv_cc.flowmesh.hub_server.presentation.response.ResDTO;
import com.jv_cc.flowmesh.hub_server.presentation.response.ResHubDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/hub")
public class HubController implements HubControllerSwagger {

    private final HubService hubService;

    @PostMapping
    public ResponseEntity<ResDTO<ResHubDTO>> createHub(@Valid @RequestBody ReqHubPostDTO dto) {
        return new ResponseEntity<>(
                ResDTO.<ResHubDTO>builder()
                        .code(HttpStatus.CREATED.value())
                        .message("허브가 생성되었습니다.")
                        .data(new ResHubDTO(hubService.createHub(dto).getHubId()))
                        .build(),
                HttpStatus.CREATED
        );
    }

    @PatchMapping("/{hubId}")
    public ResponseEntity<ResDTO<ResHubDTO>> modifyHub(@PathVariable Long hubId, ReqHubPostDTO dto) {
        return new ResponseEntity<>(
                ResDTO.<ResHubDTO>builder()
                        .code(HttpStatus.OK.value())
                        .message("허브가 수정되었습니다.")
                        .data(new ResHubDTO(hubService.modifyHub(hubId, dto).getHubId()))
                        .build(),
                HttpStatus.OK
        );
    }
}
