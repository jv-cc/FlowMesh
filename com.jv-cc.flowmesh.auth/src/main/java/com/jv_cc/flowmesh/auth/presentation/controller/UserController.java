package com.jv_cc.flowmesh.auth.presentation.controller;

import com.jv_cc.flowmesh.auth.application.dto.UserInfoDto;
import com.jv_cc.flowmesh.auth.application.util.JwtUtil;
import com.jv_cc.flowmesh.auth.domain.service.UserService;
import com.jv_cc.flowmesh.auth.presentation.response.ResDTO;
import com.jv_cc.flowmesh.auth.presentation.response.UserResDto;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
