package com.jv_cc.flowmesh.auth.infrastructor.swagger;

import com.jv_cc.flowmesh.auth.application.dto.UserInfoDto;
import com.jv_cc.flowmesh.auth.application.util.JwtUtil;
import com.jv_cc.flowmesh.auth.presentation.request.RoleReqDto;
import com.jv_cc.flowmesh.auth.presentation.request.SearchReqDto;
import com.jv_cc.flowmesh.auth.presentation.request.UserReqDto;
import com.jv_cc.flowmesh.auth.presentation.response.ResDTO;
import com.jv_cc.flowmesh.auth.presentation.response.UserResDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Tag(name = "User", description = "유저 관련 API")
@RequestMapping("/api/users")
public interface UserControllerSwagger {

    @Operation(summary = "사용자 조회", description = "자신의 사용자 정보를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "사용자 정보 조회 성공",
                    content = @Content(
                            schema = @Schema(implementation = UserInfoDto.class),
                            examples = {
                                    @ExampleObject(
                                            value = "{\n" +
                                                    "  \"code\": 200,\n" +
                                                    "  \"message\": \"사용자의 정보를 조회했습니다.\",\n" +
                                                    "  \"data\": {\n" +
                                                    "    \"user_id\": 17\n" +
                                                    "    \"username\": \"user17\"\n" +
                                                    "    \"nickname\": \"유저17\"\n" +
                                                    "    \"email\": \"user17@email.com\",\n" +
                                                    "    \"slack_id\": \"U07RFDJRQEB\"\n" +
                                                    "    \"role\": \"HUB_MANAGER\"" +
                                                    "  }\n" +
                                                    "}"
                                    )
                            }
                    )
            ),
            @ApiResponse(responseCode = "400", description = "사용자 정보 조회 실패",
                    content = @Content(
                            examples = {
                                    @ExampleObject(
                                            name = "USER_NOT_EXIST",
                                            description = "존재하지 않는 유저네임 에러",
                                            value = "{ \"code\": 1105, \"message\": \"존재하지 않는 유저네임 입니다.\" }"
                                    ),
                                    @ExampleObject(
                                            name = "AUTH_INVALID_TOKEN",
                                            description = "유효하지 않은 토큰 에러",
                                            value = "{ \"code\": 101, \"message\": \"유효하지 않은 토큰 입니다.\" }"
                                    )
                            }
                    )
            )
    })
    @GetMapping("/{users_id}")
    ResponseEntity<ResDTO<UserResDto>> selectUser(
            @Parameter(
                    name = "X-User-Id",
                    description = "게이트웨이에서 토큰 검증 후 인증된 사용자 정보의 User_Id",
                    required = true,
                    schema = @Schema(type = "number", example = "656462091419423487")
            )
            @NotNull @RequestHeader(name = JwtUtil.JwtHeader.KEY_USER_ID) String tokenUserId,
            @Parameter(
                    name = "users_id",
                    description = "정보를 조회할 사용자의 User_Id",
                    required = true,
                    schema = @Schema(type = "number", example = "656462091419423487")
            )
            @NotNull @PathVariable(value = "users_id") Long userId
    );

    @Operation(summary = "사용자 정보 변경", description = "특정 사용자 정보를 변경합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "사용자 정보 변경 성공",
                    content = @Content(
                            schema = @Schema(implementation = ResDTO.class),
                            examples = {
                                    @ExampleObject(
                                            name = "USER_NOT_EXIST",
                                            description = "존재하지 않는 유저네임 에러",
                                            value = "{ \"code\": 1105, \"message\": \"존재하지 않는 유저네임 입니다.\" }"
                                    ),
                                    @ExampleObject(
                                            value = "{\n" +
                                                    "  \"code\": 200,\n" +
                                                    "  \"message\": \"사용자의 정보가 변경됐습니다.\",\n" +
                                                    "  \"data\": {\n" +
                                                    "    \"updated_at\": \"2024-12-17T10:44:30.327959\"" +
                                                    "  }\n" +
                                                    "}"
                                    )
                            }
                    )
            ),
            @ApiResponse(responseCode = "400", description = "사용자 정보 변경 실패",
                    content = @Content(
                            examples = {
                                    @ExampleObject(
                                            name = "AUTH_INVALID_TOKEN",
                                            description = "유효하지 않은 토큰 혹은 권한 에러",
                                            value = "{ \"code\": 101, \"message\": \"유효하지 않은 토큰 입니다.\" }"
                                    )
                            }
                    )
            )
    })
    @PutMapping("/{users_id}")
    ResponseEntity<ResDTO<Map<String, String>>> updateUser(
            @Parameter(
                    name = "X-User-Role",
                    description = "게이트웨이에서 토큰 검증 후 인증된 사용자 정보의 권한",
                    required = true,
                    schema = @Schema(type = "string", example = "MASTER")
            )
            @NotNull @RequestHeader(name = JwtUtil.JwtHeader.KEY_USER_ROLE) String tokenUserRole,
            @Parameter(
                    name = "X-User-Id",
                    description = "게이트웨이에서 토큰 검증 후 인증된 사용자 정보의 User_Id",
                    required = true,
                    schema = @Schema(type = "number", example = "656462091419423487")
            )
            @NotNull @RequestHeader(name = JwtUtil.JwtHeader.KEY_USER_ID) String tokenUserId,
            @Parameter(
                    name = "users_id",
                    description = "정보를 변경할 사용자의 User_Id",
                    required = true,
                    schema = @Schema(type = "number", example = "656462091419423487")
            )
            @NotNull @PathVariable(value = "users_id") Long userId,
            @RequestBody UserReqDto reqDto
    );


    @Operation(summary = "사용자 권한 변경", description = "특정 사용자 권한을 변경합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "사용자 권한 변경 성공",
                    content = @Content(
                            schema = @Schema(implementation = ResDTO.class),
                            examples = {
                                    @ExampleObject(
                                            value = "{\n" +
                                                    "  \"code\": 200,\n" +
                                                    "  \"message\": \"사용자의 권한이 변경됐습니다.\",\n" +
                                                    "  \"data\": {\n" +
                                                    "    \"updated_at\": \"2024-12-17T10:44:30.327959\"" +
                                                    "  }\n" +
                                                    "}"
                                    )
                            }
                    )
            ),
            @ApiResponse(responseCode = "400", description = "사용자 권한 변경 실패",
                    content = @Content(
                            examples = {
                                    @ExampleObject(
                                            name = "USER_NOT_EXIST",
                                            description = "존재하지 않는 유저네임 에러",
                                            value = "{ \"code\": 1105, \"message\": \"존재하지 않는 유저네임 입니다.\" }"
                                    ),
                                    @ExampleObject(
                                            name = "AUTH_INVALID_TOKEN",
                                            description = "유효하지 않은 토큰 혹은 권한 에러",
                                            value = "{ \"code\": 101, \"message\": \"유효하지 않은 토큰 입니다.\" }"
                                    )
                            }
                    )
            )
    })
    @PatchMapping("/{users_id}")
    ResponseEntity<ResDTO<Map<String, String>>> updateRole(
            @Parameter(
                    name = "X-User-Role",
                    description = "게이트웨이에서 토큰 검증 후 인증된 사용자 정보의 권한",
                    required = true,
                    schema = @Schema(type = "string", example = "MASTER")
            )
            @NotNull @RequestHeader(name = JwtUtil.JwtHeader.KEY_USER_ROLE) String tokenUserRole,
            @Parameter(
                    name = "X-User-Id",
                    description = "게이트웨이에서 토큰 검증 후 인증된 사용자 정보의 User_Id",
                    required = true,
                    schema = @Schema(type = "number", example = "656462091419423487")
            )
            @NotNull @RequestHeader(name = JwtUtil.JwtHeader.KEY_USER_ID) String tokenUserId,
            @Parameter(
                    name = "users_id",
                    description = "권한을 변경할 사용자의 User_Id",
                    required = true,
                    schema = @Schema(type = "number", example = "656462091419423487")
            )
            @NotNull @PathVariable(value = "users_id") Long userId,
            @NotNull @RequestBody RoleReqDto reqDto
    );

    @Operation(summary = "사용자 비활성화", description = "특정 사용자를 비활성화합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "사용자 비활성화 성공",
                    content = @Content(
                            schema = @Schema(implementation = ResDTO.class),
                            examples = {
                                    @ExampleObject(
                                            value = "{\n" +
                                                    "  \"code\": 200,\n" +
                                                    "  \"message\": \"사용자가 비활성화 됐습니다.\",\n" +
                                                    "  \"data\": {\n" +
                                                    "    \"deleted_at\": \"2024-12-17T10:44:30.327959\"" +
                                                    "  }\n" +
                                                    "}"
                                    )
                            }
                    )
            ),
            @ApiResponse(responseCode = "400", description = "사용자 비활성화 실패",
                    content = @Content(
                            examples = {
                                    @ExampleObject(
                                            name = "USER_NOT_EXIST",
                                            description = "존재하지 않는 유저네임 에러",
                                            value = "{ \"code\": 1105, \"message\": \"존재하지 않는 유저네임 입니다.\" }"
                                    ),
                                    @ExampleObject(
                                            name = "AUTH_INVALID_TOKEN",
                                            description = "유효하지 않은 토큰 혹은 권한 에러",
                                            value = "{ \"code\": 101, \"message\": \"유효하지 않은 토큰 입니다.\" }"
                                    )
                            }
                    )
            )
    })
    @DeleteMapping("/{users_id}")
    ResponseEntity<ResDTO<Map<String, String>>> deleteUser(
            @Parameter(
                    name = "X-User-Role",
                    description = "게이트웨이에서 토큰 검증 후 인증된 사용자 정보의 권한",
                    required = true,
                    schema = @Schema(type = "string", example = "MASTER")
            )
            @NotNull @RequestHeader(name = JwtUtil.JwtHeader.KEY_USER_ROLE) String tokenUserRole,
            @Parameter(
                    name = "X-User-Id",
                    description = "게이트웨이에서 토큰 검증 후 인증된 사용자 정보의 User_Id",
                    required = true,
                    schema = @Schema(type = "number", example = "656462091419423487")
            )
            @NotNull @RequestHeader(name = JwtUtil.JwtHeader.KEY_USER_ID) String tokenUserId,
            @Parameter(
                    name = "users_id",
                    description = "비활성화할 사용자의 User_Id",
                    required = true,
                    schema = @Schema(type = "number", example = "656462091419423487")
            )
            @NotNull @PathVariable(value = "users_id") Long userId
    );


    @Operation(summary = "사용자 검색", description = "유저네임으로 사용자를 검색합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "사용자 검색 성공",
                    content = @Content(
                            schema = @Schema(implementation = ResDTO.class),
                            examples = {
                                    @ExampleObject(
                                            value = "{\n" +
                                                    "  \"code\": 200,\n" +
                                                    "  \"message\": \"사용자의 정보를 조회했습니다.\",\n" +
                                                    "  \"data\": {\n" +
                                                    "    \"links\": [\n" +
                                                    "      {\n" +
                                                    "        \"rel\": \"self\",\n" +
                                                    "        \"href\": \"http://localhost:19045/api/users?username=user17&limit=10&order=ASC&page=0&size=10&sort=username,asc\"\n" +
                                                    "      }\n" +
                                                    "    ],\n" +
                                                    "    \"content\": [\n" +
                                                    "      {\n" +
                                                    "        \"id\": 656753190543020859,\n" +
                                                    "        \"username\": \"user17\",\n" +
                                                    "        \"nickname\": \"유저17\",\n" +
                                                    "        \"email\": \"user17@email.com\",\n" +
                                                    "        \"slackId\": \"U17RFDJRQEB\",\n" +
                                                    "        \"role\": \"CUSTOMER\",\n" +
                                                    "        \"links\": []\n" +
                                                    "      }\n" +
                                                    "    ],\n" +
                                                    "    \"page\": {\n" +
                                                    "      \"size\": 10,\n" +
                                                    "      \"totalElements\": 1,\n" +
                                                    "      \"totalPages\": 1,\n" +
                                                    "      \"number\": 0\n" +
                                                    "    }\n" +
                                                    "  }\n" +
                                                    "}"
                                    )
                            }
                    )
            ),
            @ApiResponse(responseCode = "400", description = "사용자 검색 실패",
                    content = @Content(
                            examples = {
                                    @ExampleObject(
                                            name = "AUTH_INVALID_TOKEN",
                                            description = "유효하지 않은 토큰 혹은 권한 에러",
                                            value = "{ \"code\": 101, \"message\": \"유효하지 않은 토큰 입니다.\" }"
                                    )
                            }
                    )
            )
    })
    @GetMapping
    ResponseEntity<ResDTO<PagedModel<EntityModel<UserInfoDto>>>> searchUsers(
            @Parameter(
                    name = "X-User-Role",
                    description = "게이트웨이에서 토큰 검증 후 인증된 사용자 정보의 권한",
                    required = true,
                    schema = @Schema(type = "string", example = "MASTER")
            )
            @NotNull @RequestHeader(name = JwtUtil.JwtHeader.KEY_USER_ROLE) String tokenUserRole,
            @ModelAttribute SearchReqDto reqDto,
            @Parameter(hidden = true)
            PagedResourcesAssembler<UserInfoDto> assembler
    );
}
