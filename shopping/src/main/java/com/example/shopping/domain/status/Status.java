package com.example.shopping.domain.status;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Status {
    private Long statusId;
    private String statusName;
    private Long masterStatusId;
}
