package com.jv_cc.flowmesh.auth.domain.service;

import com.jv_cc.flowmesh.auth.application.dto.UserInfoDto;
import com.jv_cc.flowmesh.auth.application.exception.*;
import com.jv_cc.flowmesh.auth.domain.model.Auth;
import com.jv_cc.flowmesh.auth.domain.model.UserRoleEnum;
import com.jv_cc.flowmesh.auth.domain.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j(topic = "UserService")
@Service
@RequiredArgsConstructor
public class UserService {
    private final AuthRepository authRepository;

    @Transactional(readOnly = true)
    public UserInfoDto selectUser(Long userId, Long tokenUserId) {
        if (!tokenUserId.equals(userId)) {
            throw new AuthInvalidTokenException();
        }
        Auth user = getEntity(userId);
        log.info("Verified and found the user successfully, userId: {}", user.getId());

        return new UserInfoDto(
                user.getId(),
                user.getUsername(),
                user.getNickname(),
                user.getEmail(),
                user.getSlackId(),
                user.getRole()
        );
    }

    @Transactional
    public LocalDateTime updateUser(UserInfoDto infoDto, Long tokenUserId, String tokenUserRole) {
        if (!tokenUserRole.equals(UserRoleEnum.MASTER.toString())) {
            throw new AuthInvalidTokenException();
        }

        Auth user = getEntity(infoDto.getId());

        if (!user.getEmail().equals(infoDto.getEmail()) && authRepository.existsByEmail(infoDto.getEmail())) {
            throw new UserExistEmailException();
        }
        if (!user.getNickname().equals(infoDto.getNickname()) && authRepository.existsByNickname(infoDto.getNickname())) {
            throw new UserExistNicknameException();
        }
        if (!user.getSlackId().equals(infoDto.getSlackId()) && authRepository.existsBySlackId(infoDto.getSlackId())) {
            throw new UserExistSlackidException();
        }

        return user.updateUser(
                tokenUserId,
                infoDto.getNickname(),
                infoDto.getEmail(),
                infoDto.getSlackId()
        );
    }

    private Auth getEntity(Long userId) {
        return authRepository.findByIdAndIsDeletedFalse(userId)
                .orElseThrow(UserNotExistException::new);
    }
}
