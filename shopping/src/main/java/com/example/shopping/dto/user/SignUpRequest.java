package com.example.shopping.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Builder
@Data
@AllArgsConstructor
public class SignUpRequest {

    @NotEmpty(message = "이메일을 입력하지 않았습니다.")
    @Email(message = "이메일 형식이 맞지 않습니다.")
    private String userEmail;

    @NotEmpty(message = "비밀번호을 입력하지 않았습니다.")
    @Size(min = 5, message = "비밀번호을 5글자 이상 입력해주세요.")
    private String password;

    @NotEmpty(message = "이름을 입력하지 않았습니다.")
    @Size(min = 2, max = 8, message = "이름을 2 ~ 8 자 사이로 입력해주세요.")
    private String userName;

    @NotEmpty(message = "휴대폰 번호를 입력하지 않았습니다.")
    @Size(min = 3, max = 11, message = "휴대폰 번호 형식이 맞지 않습니다.")
    private String phoneNumber;

    @NotEmpty(message = "주소를 입력하지 않았습니다.")
    private String address;

}
