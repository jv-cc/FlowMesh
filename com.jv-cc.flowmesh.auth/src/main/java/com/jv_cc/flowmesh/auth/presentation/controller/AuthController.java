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

import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController implements AuthControllerSwagger {
    private final AuthService authService;

    @PostMapping("/sign-up")
    public ResponseEntity<ResDTO<SignupResDto>> signup(@Valid @RequestBody SignupReqDto signupReqDto) {
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
        log.info("Add token and user information to header");

        return new ResponseEntity<>(
                ResDTO.<SigninResDto>builder()
                        .code(HttpStatus.CREATED.value())
                        .message("로그인 정보가 생성되었습니다.")
                        .data(new SigninResDto(
                                String.valueOf(tokenDto.getCreateAt()),
                                String.valueOf(tokenDto.getId()),
                                String.valueOf(tokenDto.getRole())
                        ))
                        .build(),
                headers,
                HttpStatus.CREATED
        );
    }

    @PostMapping("/token")
    public ResponseEntity<ResDTO<Map<String, String>>> refreshToken(@RequestHeader(name= JwtUtil.JwtHeader.KEY_REFRESH_TOKEN) String refreshToken){
        AuthTokenDto tokenDto =  authService.updateRefreshToken(refreshToken);

        HttpHeaders headers = new HttpHeaders();
        headers.add(JwtUtil.JwtHeader.KEY_ACCESS_TOKEN, tokenDto.getAccessToken());
        headers.add(JwtUtil.JwtHeader.KEY_REFRESH_TOKEN, tokenDto.getRefreshToken());
        log.info("Add token information to header");

        return new ResponseEntity<>(
                ResDTO.<Map<String, String>>builder()
                        .code(HttpStatus.CREATED.value())
                        .message("새로운 토큰이 발급되었습니다.")
                        .data(Map.of(
                                "created_at",
                                tokenDto.getCreateAt().toString()
                        ))
                        .build(),
                headers,
                HttpStatus.CREATED
        );
    }
}
