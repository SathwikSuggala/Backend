package com.sathwik.Backend.dto;

import lombok.Data;
import java.util.List;

@Data
public class OrderDetails {

    private List<OrderItemDetails> items;

    @Data
    public static class OrderItemDetails {
        private Long productId;
        private int quantity;
    }
}
