package com.jv_cc.flowmesh.auth.presentation.controller;

import com.jv_cc.flowmesh.auth.application.dto.UserInfoDto;
import com.jv_cc.flowmesh.auth.application.util.JwtUtil;
import com.jv_cc.flowmesh.auth.domain.model.UserRoleEnum;
import com.jv_cc.flowmesh.auth.domain.service.UserService;
import com.jv_cc.flowmesh.auth.presentation.request.RoleReqDto;
import com.jv_cc.flowmesh.auth.presentation.request.UserReqDto;
import com.jv_cc.flowmesh.auth.presentation.response.ResDTO;
import com.jv_cc.flowmesh.auth.presentation.response.UserResDto;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@Slf4j(topic = "UserController")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/{users_id}")
    public ResponseEntity<ResDTO<UserResDto>> selectUser(
            @NotNull @RequestHeader(name = JwtUtil.JwtHeader.KEY_USER_ID) Long tokenUserId,
            @NotNull @PathVariable(value = "users_id") Long userId
    ) {
        UserInfoDto infoDto = userService.selectUser(userId, tokenUserId);
        return new ResponseEntity<>(
                ResDTO.<UserResDto>builder()
                        .code(HttpStatus.OK.value())
                        .message("사용자의 정보를 조회했습니다.")
                        .data(new UserResDto(
                                String.valueOf(infoDto.getId()),
                                infoDto.getUsername(),
                                infoDto.getNickname(),
                                infoDto.getEmail(),
                                infoDto.getSlackId(),
                                String.valueOf(infoDto.getRole())
                        ))
                        .build(),
                HttpStatus.OK
        );
    }

    @PutMapping("/{users_id}")
    public ResponseEntity<ResDTO<Map<String, String>>> updateUser(
            @NotNull @RequestHeader(name = JwtUtil.JwtHeader.KEY_USER_ROLE) UserRoleEnum tokenUserRole,
            @NotNull @RequestHeader(name = JwtUtil.JwtHeader.KEY_USER_ID) Long tokenUserId,
            @NotNull @PathVariable(value = "users_id") Long userId,
            @RequestBody UserReqDto reqDto
    ) {
        UserInfoDto infoDto = UserInfoDto
                .builder()
                .id(userId)
                .email(reqDto.getEmail())
                .nickname(reqDto.getNickname())
                .slackId(reqDto.getSlackId())
                .build();

        LocalDateTime updateAt = userService.updateUser(infoDto, tokenUserId, tokenUserRole);
        return new ResponseEntity<>(
                ResDTO.<Map<String, String>>builder()
                        .code(HttpStatus.OK.value())
                        .message("사용자의 정보가 변경됐습니다.")
                        .data(Map.of(
                                "updated_at", String.valueOf(updateAt)
                        ))
                        .build(),
                HttpStatus.OK
        );
    }

    @PatchMapping("/{users_id}")
    public ResponseEntity<ResDTO<Map<String, String>>> updateRole(
            @NotNull @RequestHeader(name = JwtUtil.JwtHeader.KEY_USER_ROLE) UserRoleEnum tokenUserRole,
            @NotNull @RequestHeader(name = JwtUtil.JwtHeader.KEY_USER_ID) Long tokenUserId,
            @NotNull @PathVariable(value = "users_id") Long userId,
            @NotNull @RequestBody RoleReqDto reqDto
    ) {
        LocalDateTime updateAt = userService.updateRole(userId, reqDto.getRole(), tokenUserId, tokenUserRole);
        return new ResponseEntity<>(
                ResDTO.<Map<String, String>>builder()
                        .code(HttpStatus.OK.value())
                        .message("사용자의 권한이 변경됐습니다.")
                        .data(Map.of(
                                "updated_at", String.valueOf(updateAt)
                        ))
                        .build(),
                HttpStatus.OK
        );
    }
}
