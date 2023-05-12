package com.enjoytrip.product.openapi;

import com.enjoytrip.product.dto.RespBodyDto;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class OpenApiResponseDto {

    private OpenApiResponse response;

}

@Getter
@Setter
class OpenApiResponse {

    private OpenApiHeader header;
    private RespBodyDto body;

}

@Getter
@Setter
class OpenApiHeader {

    private String resultCode;
    private String resultMsg;

}




