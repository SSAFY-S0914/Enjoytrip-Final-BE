package com.enjoytrip.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class CategoryDto {

    @AllArgsConstructor
    @Getter
    public static class Get {

        private Long contentTypeId;
        private String name;
    }
}
