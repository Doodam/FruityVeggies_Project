package com.fruityveggies.www.email.dto;

import com.fruityveggies.www.repository.Order;

import lombok.Data;

@Data
public class FreshmarketOrderDto {

    private String name; // 구매자이름
    private String orderphone;
    private String orderaddress1;
    private String orderaddress2;
    private String orderrequest;
    private Long orderItemId;
    
    public Order toEntity() {
        return Order.builder()
                .name(name)
                .orderphone(orderphone)
                .orderaddress1(orderaddress1)
                .orderaddress2(orderaddress2)
                .orderrequest(orderrequest)
                .orderItemId(orderItemId)
                .build();
    }
}
