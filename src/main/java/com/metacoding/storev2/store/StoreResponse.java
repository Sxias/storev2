package com.metacoding.storev2.store;

import lombok.AllArgsConstructor;
import lombok.Data;

public class StoreResponse {

    @AllArgsConstructor
    @Data
    public static class DetailDTO {
        private String name;
        private Integer stock;
        private Integer price;
    }
}
