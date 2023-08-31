package com.example.shopping.dto.user;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Builder
@Data
@AllArgsConstructor
@ToString
public class UpdatePasswordRequest {
    
    @NotEmpty(message = "기존 비밀번호를 입력하지 않았습니다.")
    private String originalPassword;
    @NotEmpty(message = "변경할 비밀번호를 입력하지 않았습니다.")
    @Size(min = 5, message = "비밀번호을 5글자 이상 입력해주세요.")
    private String updatePassword;
}
