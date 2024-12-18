package com.jv_cc.flowmesh.hub_server.presentation.controller;

import com.jv_cc.flowmesh.hub_server.application.dto.HubDTO;
import com.jv_cc.flowmesh.hub_server.application.service.HubService;
import com.jv_cc.flowmesh.hub_server.infrastructure.swagger.HubControllerSwagger;
import com.jv_cc.flowmesh.hub_server.presentation.request.ReqHubPostDTO;
import com.jv_cc.flowmesh.hub_server.presentation.request.SearchReqDto;
import com.jv_cc.flowmesh.hub_server.presentation.response.ResDTO;
import com.jv_cc.flowmesh.hub_server.presentation.response.ResHubDTO;
import com.jv_cc.flowmesh.hub_server.presentation.response.ResHubGetDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/hubs")
public class HubController implements HubControllerSwagger {

    private final HubService hubService;

    @PostMapping
    public ResponseEntity<ResDTO<ResHubDTO>> createHub(@RequestHeader("X-User-Id") String userId,
                                                       @RequestHeader("X-User-Role") String role,
                                                       @Valid @RequestBody ReqHubPostDTO dto) {
        return new ResponseEntity<>(
                ResDTO.<ResHubDTO>builder()
                        .code(HttpStatus.CREATED.value())
                        .message("허브가 생성되었습니다.")
                        .data(new ResHubDTO(hubService.createHub(Long.valueOf(userId), role, dto).getHubId()))
                        .build(),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<ResDTO<PagedModel<EntityModel<ResHubGetDTO>>>> searchHub(@ModelAttribute SearchReqDto dto,
                                                                                   PagedResourcesAssembler<ResHubGetDTO> assembler) {
        Page<ResHubGetDTO> page = hubService.searchHub(dto);

        return new ResponseEntity<>(
                ResDTO.<PagedModel<EntityModel<ResHubGetDTO>>>builder()
                        .code(HttpStatus.OK.value())
                        .message("허브 정보를 조회했습니다.")
                        .data(assembler.toModel(page))
                        .build(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{hubId}")
    public ResponseEntity<ResDTO<ResHubGetDTO>> getHub(@PathVariable Long hubId) {

        HubDTO hubDTO = hubService.getHub(hubId);

        return new ResponseEntity<>(
                ResDTO.<ResHubGetDTO>builder()
                        .code(HttpStatus.OK.value())
                        .message("상품 조회에 성공했습니다.")
                        .data(ResHubGetDTO.builder()
                                .hubId(hubDTO.getHubId())
                                .name(hubDTO.getName())
                                .address(hubDTO.getAddress())
                                .latitude(hubDTO.getLatitude())
                                .longitude(hubDTO.getLongitude())
                                .build())
                        .build(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{hubId}/client")
    public boolean getHubIdBy(@PathVariable Long hubId){
        return hubService.getHubIdBy(hubId);
    }

    @PatchMapping("/{hubId}")
    public ResponseEntity<ResDTO<ResHubDTO>> modifyHub(@RequestHeader("X-User-Id") String userId,
                                                       @RequestHeader("X-User-Role") String role,
                                                       @PathVariable Long hubId, ReqHubPostDTO dto) {
        return new ResponseEntity<>(
                ResDTO.<ResHubDTO>builder()
                        .code(HttpStatus.OK.value())
                        .message("허브가 수정되었습니다.")
                        .data(new ResHubDTO(hubService.modifyHub(Long.valueOf(userId), role, hubId, dto).getHubId()))
                        .build(),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{hubId}")
    public ResponseEntity<ResDTO<ResHubDTO>> deleteHub(@RequestHeader("X-User-Id") String userId,
                                                       @RequestHeader("X-User-Role") String role,
                                                       @PathVariable Long hubId) {
        return new ResponseEntity<>(
                ResDTO.<ResHubDTO>builder()
                        .code(HttpStatus.OK.value())
                        .message("허브가 삭제되었습니다.")
                        .data(new ResHubDTO(hubService.deleteHub(Long.valueOf(userId), role, hubId).getHubId()))
                        .build()
                , HttpStatus.OK
        );
    }
}
