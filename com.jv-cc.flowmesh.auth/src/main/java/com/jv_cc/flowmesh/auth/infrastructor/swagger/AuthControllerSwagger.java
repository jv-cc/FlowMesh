package com.jv_cc.flowmesh.auth.infrastructor.swagger;

import com.jv_cc.flowmesh.auth.application.util.JwtUtil;
import com.jv_cc.flowmesh.auth.presentation.request.SigninReqDto;
import com.jv_cc.flowmesh.auth.presentation.request.SignupReqDto;
import com.jv_cc.flowmesh.auth.presentation.response.ResDTO;
import com.jv_cc.flowmesh.auth.presentation.response.SigninResDto;
import com.jv_cc.flowmesh.auth.presentation.response.SignupResDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Tag(name = "Auth", description = "인증 관련 API")
@RequestMapping("/api/auth")
public interface AuthControllerSwagger {

    @Operation(summary = "1. 회원가입", description = "작성한 정보로 사용자를 등록합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "회원가입 성공",
                    content = @Content(
                            schema = @Schema(implementation = SignupReqDto.class), // DTO 구조
                            examples = {
                                    @ExampleObject(
                                            value = "{\n" +
                                                    "  \"code\": 201,\n" +
                                                    "  \"message\": \"사용자가 생성되었습니다.\",\n" +
                                                    "  \"data\": {\n" +
                                                    "    \"user_id\": 656753190543020859,\n" +
                                                    "    \"created_at\": \"2024-12-17T16:02:42.501720\"\n" +
                                                    "  }\n" +
                                                    "}"
                                    )
                            }
                    )
            ),
            @ApiResponse(responseCode = "400", description = "회원가입 실패",
                    content = @Content(
                            examples = {
                                    @ExampleObject(
                                            name = "USER_EXIST_USERNAME",
                                            description = "중복된 유저네임 에러",
                                            value = "{ \"code\": 1101, \"message\": \"중복되는 유저네임 입니다.\" }"
                                    ),
                                    @ExampleObject(
                                            name = "USER_EXIST_NICKNAME",
                                            description = "중복된 닉네임 에러",
                                            value = "{ \"code\": 1102, \"message\": \"중복되는 닉네임 입니다.\" }"
                                    ),
                                    @ExampleObject(
                                            name = "USER_EXIST_EMAIL",
                                            description = "중복된 이메일 에러",
                                            value = "{ \"code\": 1103, \"message\": \"중복되는 이메일 입니다.\" }"
                                    ),
                                    @ExampleObject(
                                            name = "USER_EXIST_SLACKID",
                                            description = "중복된 Slack_ID 에러",
                                            value = "{ \"code\": 1104, \"message\": \"중복되는 Slack_ID 입니다.\" }"
                                    )
                            }
                    )
            )
    })
    @PostMapping("/sign-up")
    ResponseEntity<ResDTO<SignupResDto>> signup(@Valid @RequestBody SignupReqDto signupReqDto);

    @Operation(summary = "2. 로그인", description = "username과 password 검증을 통해 토큰 정보를 발급받습니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "로그인 성공",
                    content = @Content(
                            schema = @Schema(implementation = SigninResDto.class),
                            examples = {
                                    @ExampleObject(
                                            value = "{\n" +
                                                    "  \"code\": 201,\n" +
                                                    "  \"message\": \"로그인 정보가 생성되었습니다.\",\n" +
                                                    "  \"data\": {\n" +
                                                    "    \"created_at\": \"2024-12-17T16:02:42.501720\",\n" +
                                                    "    \"user_id\": 656753190543020859,\n" +
                                                    "    \"role\": \"CUSTOMER\"\n" +
                                                    "  }\n" +
                                                    "}"
                                    )
                            }
                    ),
                    headers = {
                            @Header(
                                    name = "Authorization",
                                    description = "액세스 토큰",
                                    schema = @Schema(type = "String", example = "Bearer eyJhbGciOiJIUzUxMiJ9...")
                            ),
                            @Header(
                                    name = "X-Refresh-Token",
                                    description = "액세스 토큰을 재발급 받기 위한 위한 리프레시 토큰",
                                    schema = @Schema(type = "String", example = "Bearer eyJhbGciOiJIUzUxMiJ9...")
                            )
                    }
            ),
            @ApiResponse(responseCode = "400", description = "로그인 실패",
                    content = @Content(
                            examples = {
                                    @ExampleObject(
                                            name = "USER_NOT_EXIST",
                                            description = "존재하지 않는 유저네임 에러",
                                            value = "{ \"code\": 1105, \"message\": \"중복되는 유저네임 입니다.\" }"
                                    ),
                                    @ExampleObject(
                                            name = "USER_PASSWORD_MISMATCH",
                                            description = "비밀번호 불일치 에러",
                                            value = "{ \"code\": 1106, \"message\": \"일치하지 않은 비밀번호 입니다.\" }"
                                    )
                            }
                    )
            )
    })
    @PostMapping("/sign-in")
    ResponseEntity<ResDTO<SigninResDto>> signin(@Valid @RequestBody SigninReqDto signinReqDto);

    @Operation(summary = "3. 토큰 재발급", description = "리프레시 토큰으로 엑세스 토큰을 재발급 받습니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "재발급 성공",
                    content = @Content(
                            schema = @Schema(implementation = ResDTO.class),
                            examples = {
                                    @ExampleObject(
                                            value = "{\n" +
                                                    "  \"code\": 201,\n" +
                                                    "  \"message\": \"새로운 토큰이 발급되었습니다.\",\n" +
                                                    "  \"data\": {\n" +
                                                    "    \"created_at\": \"2024-12-17T17:02:39\"\n" +
                                                    "  }\n" +
                                                    "}"
                                    )
                            }
                    ),
                    headers = {
                            @Header(
                                    name = "Authorization",
                                    description = "재발급된 새로운 액세스 토큰",
                                    schema = @Schema(type = "String", example = "Bearer eyJhbGciOiJIUzUxMiJ9...")
                            ),
                            @Header(
                                    name = "X-Refresh-Token",
                                    description = "재발급된 새로운 리프레시 토큰",
                                    schema = @Schema(type = "String", example = "Bearer eyJhbGciOiJIUzUxMiJ9...")
                            )
                    }
            ),
            @ApiResponse(responseCode = "400", description = "재발급 실패",
                    content = @Content(
                            examples = {
                                    @ExampleObject(
                                            name = "AUTH_INVALID_TOKEN",
                                            description = "토큰 검증 에러",
                                            value = "{ \"code\": 101, \"message\": \"유효하지 않은 토큰 입니다.\" }"
                                    )
                            }
                    )
            )
    })
    @PostMapping("/token")
    ResponseEntity<ResDTO<Map<String, String>>> refreshToken(
            @Parameter(
                    name = "X-Refresh-Token",
                    description = "액세스 토큰을 재발급받기 위한 리프레시 토큰",
                    required = true,
                    schema = @Schema(type = "string", example = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJ1c2VyX2lkIjoiNjU2NDYyMDkxNDE5NDIzNDg3IiwidXNlcl9yb2xlIjoiTUFTVEVSIiwiaXNzIjoiYXV0aCIsImlhdCI6MTczNDQyMzY4MCwiZXhwIjoxNzM0NDM0NDgwfQ.6urY14JyhXSH0kOG5ld1fm33RWySYJ8ALCjZevZCLZHuoaaVEnuLffy6Pepwwj9hw3ZBqJa6yj07OIZkT-ca6A")
            )
            @RequestHeader(name = JwtUtil.JwtHeader.KEY_REFRESH_TOKEN
            ) String refreshToken);

}