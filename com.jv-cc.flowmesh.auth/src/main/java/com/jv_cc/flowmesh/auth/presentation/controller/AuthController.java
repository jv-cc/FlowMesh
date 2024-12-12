package com.jv_cc.flowmesh.auth.presentation.controller;

import com.jv_cc.flowmesh.auth.application.dto.AuthDto;
import com.jv_cc.flowmesh.auth.application.dto.AuthUserDto;
import com.jv_cc.flowmesh.auth.application.dto.UserMetaDto;
import com.jv_cc.flowmesh.auth.application.util.JwtUtil;
import com.jv_cc.flowmesh.auth.domain.service.AuthService;
import com.jv_cc.flowmesh.auth.infrastructor.swagger.AuthControllerSwagger;
import com.jv_cc.flowmesh.auth.presentation.request.SignupReqDto;
import com.jv_cc.flowmesh.auth.presentation.response.ResDTO;
import com.jv_cc.flowmesh.auth.presentation.response.SignupResDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController implements AuthControllerSwagger {
    private final AuthService authService;

    @PostMapping("/sign-up")
    public ResponseEntity<ResDTO<SignupResDto>> signup(@Valid @RequestBody SignupReqDto signupReqDto) {
        log.debug("signupReqDto: {}", signupReqDto.toString());
        UserMetaDto metaDto = authService.register(
                new AuthUserDto(
                        signupReqDto.getUsername(),
                        signupReqDto.getPassword(),
                        signupReqDto.getEmail(),
                        signupReqDto.getNickname(),
                        signupReqDto.getSlackId()
                )
        );
        log.info("User Signup Success, userId: {}", metaDto.getId());

        return new ResponseEntity<>(
                ResDTO.<SignupResDto>builder()
                        .code(HttpStatus.CREATED.value())
                        .message("사용자가 생성되었습니다.")
                        .data(new SignupResDto(
                                metaDto.getId(),
                                metaDto.getCreateAt().toString())
                        )
                        .build(),
                headers,
                HttpStatus.CREATED
        );
    }
}
