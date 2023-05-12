package com.enjoytrip.product.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ItemDto {

    private String addr1;

    private String addr2;

    @JsonProperty("areacode")
    private Long areaCode;

    private String booktour;

    private String cat1;

    private String cat2;

    private String cat3;

    @JsonProperty("contentid")
    private Long contentId;

    @JsonProperty("contenttypeid")
    private Long contentTypeId;

    @JsonProperty("createdtime")
    private Date createdTime;

    @JsonProperty("firstimage")
    private String image;

    @JsonProperty("firstimage2")
    private String image2;

    private String cpyrhtDivCd;

    @JsonProperty("mapx")
    private String mapX;

    @JsonProperty("mapy")
    private String mapY;

    private String mlevel;

    @JsonProperty("modifiedtime")
    private Date modifiedTime;

    @JsonProperty("sigungucode")
    private Long sigunguCode;

    private String tel;

    private String title;

    @JsonProperty("zipcode")
    private Long zipCode;

    private String showflag;
    
}