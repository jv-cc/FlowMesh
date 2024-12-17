package com.jv_cc.flowmesh.auth.presentation.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class SignupReqDto {

    @Schema(description = "유저네임 (4~10자) 소문자, 숫자 사용 가능", example = "rtan1218")
    @NotBlank
    @Pattern(regexp = "^[a-z0-9]{4,10}$",
            message = "(4~10자) 소문자, 숫자 사용 가능")
    private String username;

    @Schema(description = "비밀번호 (8~15자) 대소문자, 숫자, 특수문자 사용 가능", example = "#sparta1218")
    @NotBlank
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+=-]).{8,15}$",
            message = "(8~15자) 대소문자, 숫자, 특수문자 사용 가능")
    private String password;

    @Schema(example = "rtan1218@email.com")
    @NotNull
    @Email
    private String email;

    @Schema(example = "르탄1218")
    @NotBlank
    private String nickname;

    @Schema(example = "U18RFDJRQEB")
    @JsonProperty(value = "slack_id")
    @NotBlank
    private String slackId;

}
