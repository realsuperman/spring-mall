package com.example.shopping.dto.user;

import  com.example.shopping.domain.user.Consumer;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class LoginResponse {

    private Consumer loginUser;
    private String grade;
    private double discountRate;

    public LoginResponse(Consumer loginUser, String grade, double discountRate) {
        this.loginUser = loginUser;
        this.grade = grade;
        this.discountRate = discountRate;
    }

    public LoginResponse(Consumer loginUser) {
        this.loginUser = loginUser;
    }


}
