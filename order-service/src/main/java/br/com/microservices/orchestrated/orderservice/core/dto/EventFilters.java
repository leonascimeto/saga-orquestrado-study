package br.com.microservices.orchestrated.orderservice.core.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EventFilters {
    private String orderId;
    private String transactionId;
}
