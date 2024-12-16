package com.jv_cc.flowmesh.hub_server.infrastructure.swagger;

import com.jv_cc.flowmesh.hub_server.presentation.request.ReqHubPostDTO;
import com.jv_cc.flowmesh.hub_server.presentation.response.ResDTO;
import com.jv_cc.flowmesh.hub_server.presentation.response.ResHubDTO;
import com.jv_cc.flowmesh.hub_server.presentation.response.ResHubGetDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Hub", description = "허브 생성, 검색, 수정, 삭제 관련 사용자 API")
public interface HubControllerSwagger {

    @Operation(summary = "허브 생성", description = "사용자 ID를 통해 허브를 생성하는 API 입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "허브 생성 성공", content = @Content(schema = @Schema(implementation = ResDTO.class))),
            @ApiResponse(responseCode = "400", description = "허브 생성 실패.", content = @Content(schema = @Schema(implementation = ResDTO.class)))
    })
    @PostMapping("/api/hub")
    ResponseEntity<ResDTO<ResHubDTO>> createHub(@RequestHeader("X-User-Id") Long userId,
                                                @RequestBody ReqHubPostDTO dto);

    @Operation(summary = "허브 단건 조회", description = "허브를 단건 조회하는 API 입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "허브 수정 성공", content = @Content(schema = @Schema(implementation = ResDTO.class))),
            @ApiResponse(responseCode = "400", description = "허브 수정 실패.", content = @Content(schema = @Schema(implementation = ResDTO.class)))
    })
    @GetMapping("/api/hub/{hubId}")
    ResponseEntity<ResDTO<ResHubGetDTO>> getHub(@PathVariable Long hubId);

    @Operation(summary = "허브 수정", description = "사용자 ID를 통해 허브를 수정하는 API 입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "허브 수정 성공", content = @Content(schema = @Schema(implementation = ResDTO.class))),
            @ApiResponse(responseCode = "400", description = "허브 수정 실패.", content = @Content(schema = @Schema(implementation = ResDTO.class)))
    })
    @PatchMapping("/api/hub/{hubId}")
    ResponseEntity<ResDTO<ResHubDTO>> modifyHub(@PathVariable Long hubId, @RequestBody ReqHubPostDTO dto);

    @Operation(summary = "허브 삭제", description = "사용자 ID를 통해 허브를 삭제하는 API 입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "허브 삭제 성공", content = @Content(schema = @Schema(implementation = ResDTO.class))),
            @ApiResponse(responseCode = "400", description = "허브 삭제 실패.", content = @Content(schema = @Schema(implementation = ResDTO.class)))
    })
    @DeleteMapping("/api/hub/{hubId}")
    ResponseEntity<ResDTO<ResHubDTO>> deleteHub(@PathVariable Long hubId);
}
