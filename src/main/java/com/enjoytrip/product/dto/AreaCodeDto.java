package com.enjoytrip.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

public class AreaCodeDto {

    @AllArgsConstructor
    @Getter
    @ToString
    public static class Get {

        private Long code;
        private String name;
        private List<SigugunCodeDto.Get> sigugunCodeList;

    }
}
