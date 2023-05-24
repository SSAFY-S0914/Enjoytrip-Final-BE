package com.enjoytrip.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class SigugunCodeDto {

    @AllArgsConstructor
    @Getter
    public static class Get {

        private Long code;
        private String name;

    }
}
