package com.example.shopping.dto.order;

import com.example.shopping.util.KakaoPayVO;
import lombok.*;

import java.util.List;

@Data
@Builder
public class OrderRequestDto {
    OrderInfoDto orderInfoDto;
    KakaoPayVO kakaoPayVO;
    List<OrderItemDto> orderItemDtoList;
}
