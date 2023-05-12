package com.enjoytrip.product.openapi;

import com.enjoytrip.product.dto.ResponseBodyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Map;


@Component
@RequiredArgsConstructor
public class TourApiClient {

    private final RestTemplate restTemplate;

    static private final String BASE_URL = "http://apis.data.go.kr/B551011/KorService1";
    static private final String MOBILEOS = "ETC";
    static private final String MOBILEAPP = "Enjoytrip";
    static private final String TYPE = "json";

    static private String API_KEY;

    @Value("${openapi.key}")
    public void setApiKey(String apiKey) {
        API_KEY = apiKey;
    }


    public ResponseBodyDto searchLocation(Map<String, String> queryParams) {

        String requestUri = "/localcationBaseList1";

        URI uri = createUri(requestUri, queryParams);

        return requestOpenApi(uri);
    }

    public ResponseBodyDto searchKeyword(Map<String, String> queryParams) {

        String requestUri = "/searchKeyword1";

        URI uri = createUri(requestUri, queryParams);

        return requestOpenApi(uri);

    }

    public ResponseBodyDto searchFestival(Map<String, String> queryParams) {

        String requestUri = "/searchFestival1";

        URI uri = createUri(requestUri, queryParams);

        return requestOpenApi(uri);

    }

    public ResponseBodyDto searchStay(Map<String, String> queryParams) {

        String requestUri = "/searchStay1";

        URI uri = createUri(requestUri, queryParams);

        return requestOpenApi(uri);

    }


    public ResponseBodyDto searchArea(Map<String, String> queryParams) {

        String requestUri = "/areaBasedList1";

        URI uri = createUri(requestUri, queryParams);

        return requestOpenApi(uri);

    }

    private static URI createUri(String requestUri, Map<String, String> params) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(BASE_URL + requestUri)
                .queryParam("MobileOS", MOBILEOS)
                .queryParam("MobileApp", MOBILEAPP)
                .queryParam("_type", TYPE)
                .queryParam("serviceKey", API_KEY);

        for (Map.Entry<String, String> entry : params.entrySet()) {
            builder.queryParam(entry.getKey(), entry.getValue());
        }

        return builder.build(true).toUri();

    }

    private ResponseBodyDto requestOpenApi(URI uri) {
        return restTemplate.exchange(
                uri,
                HttpMethod.GET,
                null,
                OpenApiResponseDto.class).getBody().getResponse().getBody();
    }
}
