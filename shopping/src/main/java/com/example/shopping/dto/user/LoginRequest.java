package com.example.shopping.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Builder
@Data
@AllArgsConstructor
public class LoginRequest {
    @NotEmpty(message = "이메일을 입력하지 않았습니다.")
    private String userEmail;
    @NotEmpty(message = "비밀번호을 입력하지 않았습니다.")
    private String password;

}
