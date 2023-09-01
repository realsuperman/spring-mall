package com.example.shopping.domain.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Membership {

    private Long membershipId;
    private String grade;
    private double discountRate;
    private long requirementLow;
    private long requirementHigh;
}
