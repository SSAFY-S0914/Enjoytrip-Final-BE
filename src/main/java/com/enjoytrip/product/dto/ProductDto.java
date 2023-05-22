package com.enjoytrip.product.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class ProductDto {

    /* 공통 정보 (키워드 기반, 지역 기반) */
    @JsonProperty("contentid")
    private Long contentId;
    @JsonProperty("contenttypeid")

    private Long contentTypeId;
    private String title;
    private String addr1;
    private String addr2;
    @JsonProperty("areacode")
    private Long areaCode;
    @JsonProperty("sigungucode")
    private Long sigunguCode;
    @JsonProperty("createdtime")
    private Date createdTime;
    @JsonProperty("modifiedtime")
    private Date modifiedTime;
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
    private String tel;
    private String booktour;
    private String cat1;
    private String cat2;
    private String cat3;
    private String zipcode; //지역 기반, 상세 정보 조회

    /* 위치 기반 조회 */
    private String dist;
    /* 위치 기반 조회 끝 */

    /* 키워드 기반 조회 */
    /* 키워드 기반 조회 끝 */

    /* 행사 정보 조회  */
    @JsonProperty("eventstartdate")
    private Date eventStartDate;
    @JsonProperty("eventenddate")
    private Date eventEndDate;
    /* 행사 정보 조회 끝 */

    /* 숙박 정보 조회 */
    private String benikia;
    private String goodstay;
    private String hanok;
    /* 숙박 정보 조회 끝 */

    /* 지역기반 관광정보 조회 */
    /* 지역기반 관광정보 끝 */

    /* 상세 정보 조회 */
    private String telname;
    private String homepage;
    private String overview;
    /* 상세 정보 조회 끝 */
}
