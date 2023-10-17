package com.example.userservice.vo;

import lombok.Data;

import java.util.Date;

@Data
public class ResponseOrder {
    private String productId; //상품 ID
    private Integer qty; // 주문 수량
    private Integer unitPrice; // 단가
    private Integer totalPrice;
    private Date createdAt; // 주문 날짜

    private String orderId; //주문 ID

}
