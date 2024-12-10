package com.jv_cc.flowmesh.hub_server.infrastructure.swagger;

import com.jv_cc.flowmesh.hub_server.application.dto.ResDTO;
import com.jv_cc.flowmesh.hub_server.presentation.request.ReqHubPostDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@Tag(name = "Hub", description = "허브 생성, 검색, 수정, 삭제 관련 사용자 API")
public interface HubControllerSwagger {

    @Operation(summary = "허브 생성", description = "사용자 ID를 통해 메뉴를 생성하는 API 입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "허브 생성 성공", content = @Content(schema = @Schema(implementation = ResDTO.class))),
            @ApiResponse(responseCode = "400", description = "허브 생성 실패.", content = @Content(schema = @Schema(implementation = ResDTO.class)))
    })
    @PostMapping("/api/hub")
    ResponseEntity<ResDTO<Long>> createHub(@RequestBody ReqHubPostDTO dto);

}
