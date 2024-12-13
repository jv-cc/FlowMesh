package com.jv_cc.flowmesh.auth.domain.service;

import com.jv_cc.flowmesh.auth.application.dto.UserInfoDto;
import com.jv_cc.flowmesh.auth.application.exception.AuthInvalidTokenException;
import com.jv_cc.flowmesh.auth.application.exception.UserNotExistException;
import com.jv_cc.flowmesh.auth.domain.model.Auth;
import com.jv_cc.flowmesh.auth.domain.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j(topic = "UserService")
@Service
@RequiredArgsConstructor
public class UserService {
    private final AuthRepository authRepository;

    @Transactional(readOnly = true)
    public UserInfoDto selectUser(Long userId, Long tokenUserId) {
        if(!tokenUserId.equals(userId)){
            throw new AuthInvalidTokenException();
        }
        Auth user = authRepository.findByIdAndIsDeletedFalse(userId)
                .orElseThrow(UserNotExistException::new);
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

}
