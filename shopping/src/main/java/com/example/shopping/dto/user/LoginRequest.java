package com.example.shopping.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@Builder
@Data
@AllArgsConstructor
public class LoginRequest {
    private String userEmail;
    private String password;

}
