package com.enjoytrip.product.openapi;

import com.enjoytrip.product.dto.ResponseBodyDto;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class OpenApiResponseWrapper {

    private OpenApiResponse response;

}

@Getter
@Setter
class OpenApiResponse {

    private OpenApiHeader header;
    private ResponseBodyDto body;

}

@Getter
@Setter
class OpenApiHeader {

    private String resultCode;
    private String resultMsg;

}




