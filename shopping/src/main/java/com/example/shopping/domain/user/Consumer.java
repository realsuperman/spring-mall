package com.example.shopping.domain.user;

import com.example.shopping.dto.user.SignUpRequest;
import com.example.shopping.dto.user.UpdatePasswordRequest;
import com.example.shopping.dto.user.UpdateUserRequest;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@Data
@ToString
public class Consumer {

    private Long consumerId;
    private String userEmail;
    private String password;
    private String phoneNumber;
    private String address;
    private String userName;
    private Integer isAdmin;

    public static Consumer signUpDtoToConsumer(SignUpRequest signUpDto) {
        return Consumer.builder().userEmail(signUpDto.getUserEmail()).password(signUpDto.getPassword()).phoneNumber(signUpDto.getPhoneNumber()).address(signUpDto.getAddress()).userName(signUpDto.getUserName()).isAdmin(0).build();
    }

    public static Consumer updateUserPassDtoToConsumer(String email, UpdatePasswordRequest updateUserRequest) {
        return Consumer.builder().userEmail(email).password(updateUserRequest.getUpdatePassword()).build();
    }

    public static Consumer updateUserInfoDtoToConsumer(String email, UpdateUserRequest updateUserRequest) {
        return Consumer.builder().userEmail(email).address(updateUserRequest.getUpdateAddress() + " " + updateUserRequest.getUpdateAddressDetail()).phoneNumber(updateUserRequest.getUpdatePhoneNumber()).build();
    }
}
