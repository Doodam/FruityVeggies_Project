package com.fruityveggies.www.dto;


import java.util.List;

import com.fruityveggies.www.repository.rescue.RescueOrder;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class RescueOrderDto {
    
    
    private String boxsize;
    private String weeksize;
    private long boxprice;
    private String dislikeid1;
    private String dislikeid2;
    private String dislikeid3;
    private String recipient;
    private String phone;
    private String address;
    private String addressdetail;
    private String resId;
    
    public RescueOrder toEntity() {
        return RescueOrder.builder()
                    .boxsize(boxsize)
                    .weeksize(weeksize)
                    .box_price(boxprice)
                    .dislikeid_1(dislikeid1)
                    .dislikeid_2(dislikeid2)
                    .dislikeid_3(dislikeid3)
                    .recipient(recipient)
                    .phone(phone)
                    .address(address)
                    .address_detail(addressdetail)
                    .userid(resId)
                    .build();
    }
    

}