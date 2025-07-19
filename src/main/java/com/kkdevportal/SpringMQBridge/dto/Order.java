package com.kkdevportal.SpringMQBridge.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class Order {
    private String orderId;
    private String name;
    private int qty;
    private  double price;
}
