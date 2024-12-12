package com.jv_cc.flowmesh.auth.domain.service;

import com.jv_cc.flowmesh.auth.application.dto.AuthTokenDto;
import com.jv_cc.flowmesh.auth.application.dto.AuthUserDto;
import com.jv_cc.flowmesh.auth.application.dto.UserMetaDto;
import com.jv_cc.flowmesh.auth.application.exception.*;
import com.jv_cc.flowmesh.auth.application.util.JwtUtil;
import com.jv_cc.flowmesh.auth.domain.model.Auth;
import com.jv_cc.flowmesh.auth.domain.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j(topic = "AuthService")
@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserMetaDto register(AuthUserDto userDto) {
        if (authRepository.existsByUsername(userDto.getUsername())) {
            throw new UserExistUsernameException();
        }
        if (authRepository.existsByEmail(userDto.getEmail())) {
            throw new UserExistEmailException();
        }
        if (authRepository.existsByNickname(userDto.getNickname())) {
            throw new UserExistNicknameException();
        }
        if (authRepository.existsBySlackId(userDto.getSlack_id())) {
            throw new UserExistSlackidException();
        }
        log.info("Duplicate scan completed");

        Auth auth = Auth.builder()
                .username(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .email(userDto.getEmail())
                .nickname(userDto.getNickname())
                .slackId(userDto.getSlack_id())
                .build();
        auth = authRepository.save(auth);
        log.info("Auth registered successfully");

        return new UserMetaDto(auth.getId(), auth.getCreatedAt());
    }

    @Transactional
    public AuthTokenDto login(String username, String password) {
        Auth auth = this.getEntity(username);
        log.info("Exist username: {}", username);

        if (!passwordEncoder.matches(password, auth.getPassword())) {
            throw new UserPasswordMismatchException();
        }
        log.info("Encrypted password match completed ");

        String refreshToken = jwtUtil.generateRefreshToken(auth.getId(), auth.getRole());
        auth.updateRefreshToken(refreshToken);
        authRepository.save(auth);

        return new AuthTokenDto(
                auth.getId(),
                auth.getRole(),
                jwtUtil.generateAccessToken(auth.getId(), auth.getRole()),
                refreshToken,
                jwtUtil.getIssuedAtFromToken(refreshToken)
        );
    }

    public AuthTokenDto updateRefreshToken(String refreshToken) {
        if (!jwtUtil.validateToken(refreshToken)) {
            throw new AuthInvalidTokenException();
        }
        log.info("Validate token completed");

        Auth auth = this.getEntityByToken(refreshToken);
        log.info("Load Auth entity completed");

        refreshToken = jwtUtil.generateRefreshToken(auth.getId(), auth.getRole());
        auth.updateRefreshToken(refreshToken);
        authRepository.save(auth);
        log.info("Auth updated successfully");

        String accessToken = jwtUtil.generateAccessToken(auth.getId(), auth.getRole());
        LocalDateTime issuedAt = jwtUtil.getIssuedAtFromToken(refreshToken);

        return new AuthTokenDto(
            auth.getId(),
                auth.getRole(),
                accessToken,
                auth.getRefreshToken(),
                issuedAt
        );
    }

    private Auth getEntity(String username) {
        return authRepository.findByUsernameAndIsDeletedFalse(username)
                .orElseThrow(UserNotExistException::new);
    }

    private Auth getEntityByToken(String refreshToken) {
        return authRepository.findByRefreshTokenAndIsDeletedFalse(refreshToken)
                .orElseThrow(AuthInvalidTokenException::new);
    }

}
