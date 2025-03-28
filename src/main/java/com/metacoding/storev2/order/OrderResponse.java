package com.metacoding.storev2.order;

import lombok.AllArgsConstructor;
import lombok.Data;

public class OrderResponse {

    @AllArgsConstructor
    @Data
    public static class OrderDTO {
        private Integer id;
        private String name;
        private Integer qty;
        private Integer totalPrice;
    }
}
