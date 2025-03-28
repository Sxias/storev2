package com.metacoding.storev2.store;

import lombok.Data;

public class StoreRequest {

    @Data
    public static class SaveDTO {
        private String name;
        private Integer stock;
        private Integer price;
    }
}
