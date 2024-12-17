package com.jv_cc.flowmesh.auth.domain.service;

import com.jv_cc.flowmesh.auth.application.dto.UserInfoDto;
import com.jv_cc.flowmesh.auth.application.exception.*;
import com.jv_cc.flowmesh.auth.domain.model.Auth;
import com.jv_cc.flowmesh.auth.domain.model.UserRoleEnum;
import com.jv_cc.flowmesh.auth.domain.repository.AuthRepository;
import com.jv_cc.flowmesh.auth.presentation.request.SearchReqDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public LocalDateTime updateUser(UserInfoDto infoDto, Long tokenUserId, UserRoleEnum tokenUserRole) {
        this.requireMaster(tokenUserRole);

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

    @Transactional
    public LocalDateTime updateRole(Long userId, UserRoleEnum role, Long tokenUserId, UserRoleEnum tokenUserRole) {
        this.requireMaster(tokenUserRole);

        Auth user = this.getEntity(userId);
        user.updateRole(tokenUserId, role);
        log.info("Role changed successfully, userId: {}, role: {}", user.getId(), user.getRole());

        return user.getUpdatedAt();
    }

    @Transactional
    public LocalDateTime deleteUser(Long userId, Long tokenUserId, UserRoleEnum tokenUserRole) {
        this.requireMaster(tokenUserRole);

        Auth user = this.getEntity(userId);
        user.markAsDelete(tokenUserId);
        log.info("User soft deleted successfully, userId: {}", user.getId());

        return user.getDeletedAt();
    }

    @Transactional(readOnly = true)
    public Page<UserInfoDto> searchUser(SearchReqDto reqDto, UserRoleEnum tokenUserRole) {
        requireMaster(tokenUserRole);

        Sort sort = Sort.by(reqDto.getOrder().getDirection(), reqDto.getSort().getLabel());
        Pageable pageable = PageRequest.of(
                reqDto.getPage(), this.validatePageLimit(reqDto.getLimit()), sort
        );
        Page<Auth> userList = authRepository.findAllByUsernameAndIsDeletedFalse(reqDto.getUsername(), pageable);
        log.info("Searched user list size: {}", userList.getTotalElements());

        return userList.map(UserInfoDto::new);
    }

    private Auth getEntity(Long userId) {
        return authRepository.findByIdAndIsDeletedFalse(userId)
                .orElseThrow(UserNotExistException::new);
    }

    private void requireMaster(UserRoleEnum roleEnum) {
        if (roleEnum.equals(UserRoleEnum.MASTER)) {
            log.info("Master permission verified");
        } else {
            throw new AuthInvalidTokenException();
        }
    }

    private Integer validatePageLimit(Integer limit) {
        if (limit == 10 || limit == 30 || limit == 50) {
            return limit;
        }
        return 10;
    }
}
