package com.kkdevportal.SpringMQBridge.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class OrderStatus {
    private Order order;
    private String status;
    private String message;
}
