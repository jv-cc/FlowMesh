package com.jv_cc.flowmesh.auth.presentation.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class SignupReqDto {

    @NotBlank
    @Pattern(regexp = "^[a-z0-9]{4,10}$",
            message = "(4~10자) 소문자, 숫자 사용 가능")
    private String username;

    @NotBlank
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+=-]).{8,15}$",
            message = "(8~15자) 대소문자, 숫자, 특수문자 사용 가능")
    private String password;

    @NotNull
    @Email
    private String email;

    @NotBlank
    private String nickname;

    @JsonProperty(value = "slack_id")
    @NotBlank
    private String slackId;

}
