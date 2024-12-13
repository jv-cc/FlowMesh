package com.jv_cc.flowmesh.auth.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class UserMetaDto {
    private Long id;
    private LocalDateTime createAt;
}
