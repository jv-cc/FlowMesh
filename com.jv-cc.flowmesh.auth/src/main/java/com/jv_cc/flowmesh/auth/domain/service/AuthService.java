package com.jv_cc.flowmesh.auth.domain.service;

import com.jv_cc.flowmesh.auth.application.dto.AuthDto;
import com.jv_cc.flowmesh.auth.application.exception.UserExistEmailException;
import com.jv_cc.flowmesh.auth.application.exception.UserExistNicknameException;
import com.jv_cc.flowmesh.auth.application.exception.UserExistSlackidException;
import com.jv_cc.flowmesh.auth.application.exception.UserExistUsernameException;
import com.jv_cc.flowmesh.auth.application.util.JwtUtil;
import com.jv_cc.flowmesh.auth.domain.model.Auth;
import com.jv_cc.flowmesh.auth.domain.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j(topic = "AuthService")
@Service
@RequiredArgsConstructor
public class AuthService{

    private final AuthRepository authRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public AuthDto register(AuthDto authDto) {
        if (authRepository.existsByUsername(authDto.getUsername())) {
            log.error("Username already exists");
            throw new UserExistUsernameException();
        }

        if (authRepository.existsByEmail(authDto.getEmail())) {
            log.error("Email already exists");
            throw new UserExistEmailException();
        }

        if (authRepository.existsByNickname(authDto.getNickname())) {
            log.error("Nickname already exists");
            throw new UserExistNicknameException();
        }

        if (authRepository.existsBySlackId(authDto.getSlack_id())) {
            log.error("Slack_id already exists");
            throw new UserExistSlackidException();
        }
        log.info("Duplicate scan completed");

        authDto.setPassword(
                passwordEncoder.encode(authDto.getPassword())
        );
        log.info("Password encryption completed");

        Auth auth = authDto.toEntity();
        auth = authRepository.save(auth);

        auth.updateRefreshToken(
                jwtUtil.generateRefreshToken(auth.getId(), auth.getRole())
        );
        log.info("Refresh token generate completed");

        return AuthDto.fromEntity(
                auth,
                jwtUtil.generateAccessToken(auth.getId(), auth.getRole())
        );
    }
}
