package com.ecommerce.platform.common.dto.event;


import com.ecommerce.platform.common.enums.PaymentStatus;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentEvent {
    private String orderId;
    private String paymentId;
    private PaymentStatus status;
}
