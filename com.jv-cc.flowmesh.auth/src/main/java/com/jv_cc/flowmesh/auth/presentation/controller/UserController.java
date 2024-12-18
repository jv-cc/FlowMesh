package com.jv_cc.flowmesh.auth.presentation.controller;

import com.jv_cc.flowmesh.auth.application.dto.UserInfoDto;
import com.jv_cc.flowmesh.auth.application.util.JwtUtil;
import com.jv_cc.flowmesh.auth.domain.model.UserRoleEnum;
import com.jv_cc.flowmesh.auth.domain.service.UserService;
import com.jv_cc.flowmesh.auth.infrastructor.swagger.UserControllerSwagger;
import com.jv_cc.flowmesh.auth.presentation.request.RoleReqDto;
import com.jv_cc.flowmesh.auth.presentation.request.SearchReqDto;
import com.jv_cc.flowmesh.auth.presentation.request.UserReqDto;
import com.jv_cc.flowmesh.auth.presentation.response.ResDTO;
import com.jv_cc.flowmesh.auth.presentation.response.UserResDto;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@Slf4j(topic = "UserController")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController implements UserControllerSwagger {
    private final UserService userService;

    @GetMapping("/{users_id}")
    public ResponseEntity<ResDTO<UserResDto>> selectUser(
            @NotNull @RequestHeader(name = JwtUtil.JwtHeader.KEY_USER_ID) String tokenUserId,
            @NotNull @PathVariable(value = "users_id") Long userId
    ) {
        UserInfoDto infoDto = userService.selectUser(userId, Long.valueOf(tokenUserId));
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
            @NotNull @RequestHeader(name = JwtUtil.JwtHeader.KEY_USER_ROLE) String tokenUserRole,
            @NotNull @RequestHeader(name = JwtUtil.JwtHeader.KEY_USER_ID) String tokenUserId,
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
        LocalDateTime updateAt = userService.updateUser(infoDto, Long.valueOf(tokenUserId), UserRoleEnum.valueOf(tokenUserRole));

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
            @NotNull @RequestHeader(name = JwtUtil.JwtHeader.KEY_USER_ROLE) String tokenUserRole,
            @NotNull @RequestHeader(name = JwtUtil.JwtHeader.KEY_USER_ID) String tokenUserId,
            @NotNull @PathVariable(value = "users_id") Long userId,
            @NotNull @RequestBody RoleReqDto reqDto
    ) {
        LocalDateTime updateAt = userService.updateRole(userId, reqDto.getRole(), Long.valueOf(tokenUserId), UserRoleEnum.valueOf(tokenUserRole));

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

    @DeleteMapping("/{users_id}")
    public ResponseEntity<ResDTO<Map<String, String>>> deleteUser(
            @NotNull @RequestHeader(name = JwtUtil.JwtHeader.KEY_USER_ROLE) String tokenUserRole,
            @NotNull @RequestHeader(name = JwtUtil.JwtHeader.KEY_USER_ID) String tokenUserId,
            @NotNull @PathVariable(value = "users_id") Long userId
    ) {
        LocalDateTime deletedAt = userService.deleteUser(userId, Long.valueOf(tokenUserId), UserRoleEnum.valueOf(tokenUserRole));

        return new ResponseEntity<>(
                ResDTO.<Map<String, String>>builder()
                        .code(HttpStatus.OK.value())
                        .message("사용자가 비활성화 됐습니다.")
                        .data(Map.of(
                                "deleted_at", String.valueOf(deletedAt)
                        ))
                        .build(),
                HttpStatus.OK
        );
    }

    @GetMapping
    public ResponseEntity<ResDTO<PagedModel<EntityModel<UserInfoDto>>>> searchUsers(
            @NotNull @RequestHeader(name = JwtUtil.JwtHeader.KEY_USER_ROLE) String tokenUserRole,
            @ModelAttribute SearchReqDto reqDto,
            PagedResourcesAssembler<UserInfoDto> assembler
    ) {
        Page<UserInfoDto> page = userService.searchUser(reqDto, UserRoleEnum.valueOf(tokenUserRole));

        return new ResponseEntity<>(
                ResDTO.<PagedModel<EntityModel<UserInfoDto>>>builder()
                        .code(HttpStatus.OK.value())
                        .message("사용자의 정보를 조회했습니다.")
                        .data(assembler.toModel(page))
                        .build(),
                HttpStatus.OK
        );
    }
}
