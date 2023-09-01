package com.example.shopping.domain.cargo;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Cargo {
    private Long cargoId;
    private Long itemId;
    private Long statusId;
}
