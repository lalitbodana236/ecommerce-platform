package com.ecommerce.platform.common.dto.event;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderCreatedEvent {
    private String orderId;
    private String userId;
   // private List<OrderItem> items;
    private double amount;
}
