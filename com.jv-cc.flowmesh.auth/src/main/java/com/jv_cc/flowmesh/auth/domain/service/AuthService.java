package com.jv_cc.flowmesh.auth.domain.service;

import com.jv_cc.flowmesh.auth.application.dto.AuthDto;

public interface AuthService {
    AuthDto register(AuthDto authDto);
}
