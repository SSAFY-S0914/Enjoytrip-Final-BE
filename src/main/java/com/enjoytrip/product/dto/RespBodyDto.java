package com.enjoytrip.product.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RespBodyDto {

    private Items items;
    private Integer numOfRows;
    private Integer pageNo;
    private Integer totalCount;

}

@Getter
@Setter
class Items {

    private List<ItemDto> items;

}
