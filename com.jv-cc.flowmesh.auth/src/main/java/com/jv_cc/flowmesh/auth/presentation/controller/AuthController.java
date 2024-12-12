package com.jv_cc.flowmesh.auth.presentation.controller;

import com.jv_cc.flowmesh.auth.application.dto.AuthTokenDto;
import com.jv_cc.flowmesh.auth.application.dto.AuthUserDto;
import com.jv_cc.flowmesh.auth.application.dto.UserMetaDto;
import com.jv_cc.flowmesh.auth.application.util.JwtUtil;
import com.jv_cc.flowmesh.auth.domain.service.AuthService;
import com.jv_cc.flowmesh.auth.infrastructor.swagger.AuthControllerSwagger;
import com.jv_cc.flowmesh.auth.presentation.request.SigninReqDto;
import com.jv_cc.flowmesh.auth.presentation.request.SignupReqDto;
import com.jv_cc.flowmesh.auth.presentation.response.ResDTO;
import com.jv_cc.flowmesh.auth.presentation.response.SigninResDto;
import com.jv_cc.flowmesh.auth.presentation.response.SignupResDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
                HttpStatus.CREATED
        );
    }

    @PostMapping("/sign-in")
    public ResponseEntity<ResDTO<SigninResDto>> signin(@Valid @RequestBody SigninReqDto signinReqDto){
        AuthTokenDto tokenDto = authService.login(
                signinReqDto.getUsername(),
                signinReqDto.getPassword()
        );

        HttpHeaders headers = new HttpHeaders();
        headers.add(JwtUtil.JwtHeader.KEY_ACCESS_TOKEN, tokenDto.getAccessToken());
        headers.add(JwtUtil.JwtHeader.KEY_REFRESH_TOKEN, tokenDto.getRefreshToken());
        headers.add(JwtUtil.JwtHeader.KEY_USER_ID, String.valueOf(tokenDto.getId()));
        headers.add(JwtUtil.JwtHeader.KEY_USER_ROLE, String.valueOf(tokenDto.getRole()));
        log.info("Add token information to header");

        return new ResponseEntity<>(
                ResDTO.<SigninResDto>builder()
                        .code(HttpStatus.CREATED.value())
                        .message("로그인 정보가 생성되었습니다.")
                        .data(new SigninResDto(
                                tokenDto.getCreateAt().toString())
                        )
                        .build(),
                headers,
                HttpStatus.CREATED
        );
    }

}
