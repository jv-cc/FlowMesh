package com.jv_cc.flowmesh.hub_server.presentation.controller;

import com.jv_cc.flowmesh.hub_server.application.dto.HubDTO;
import com.jv_cc.flowmesh.hub_server.application.service.HubService;
import com.jv_cc.flowmesh.hub_server.infrastructure.swagger.HubControllerSwagger;
import com.jv_cc.flowmesh.hub_server.presentation.request.ReqHubPostDTO;
import com.jv_cc.flowmesh.hub_server.presentation.response.ResDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/hub")
public class HubController implements HubControllerSwagger {

    private final HubService hubService;

    @PostMapping
    public ResponseEntity<ResDTO<HubDTO>> createHub(@Valid @RequestBody ReqHubPostDTO dto) {
        return new ResponseEntity<>(
                ResDTO.<HubDTO>builder()
                        .code(HttpStatus.OK.value())
                        .message("허브가 생성되었습니다.")
                        .data(hubService.createHub(dto))
                        .build(),
                HttpStatus.CREATED
        );
    }
}
