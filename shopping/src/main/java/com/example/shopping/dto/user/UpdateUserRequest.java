package com.example.shopping.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Builder
@Data
@AllArgsConstructor
@ToString
public class UpdateUserRequest {

    @NotEmpty(message = "주소를 입력하지 않았습니다.")
    private String updateAddress;
    private String updateAddressDetail;
    @NotEmpty(message = "휴대폰 번호를 입력하지 않았습니다.")
    @Size(min = 3, max = 11, message = "휴대폰 번호 형식이 맞지 않습니다.")
    private String updatePhoneNumber;

}
