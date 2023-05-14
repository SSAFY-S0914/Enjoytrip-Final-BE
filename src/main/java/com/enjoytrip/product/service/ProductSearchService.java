package com.enjoytrip.product.service;

import com.enjoytrip.product.dto.ResponseBodyDto;
import com.enjoytrip.product.openapi.TourApiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProductSearchService {

    private final TourApiClient apiClient;
    private List<String> requiredParams;

    public ResponseBodyDto searchLocation(Map<String, String> queryParams) {

        requiredParams = new ArrayList<>();
        requiredParams.add("mapX");
        requiredParams.add("mapY");
        requiredParams.add("radius");

        checkRequiredParams(requiredParams, queryParams);

        return apiClient.searchLocation(queryParams);
    }

    public ResponseBodyDto searchKeyword(Map<String, String> queryParams) {

        requiredParams = new ArrayList<>();
        requiredParams.add("keyword");

        checkRequiredParams(requiredParams, queryParams);

        return apiClient.searchKeyword(queryParams);
    }

    public ResponseBodyDto searchFestival(Map<String, String> queryParams) {

        requiredParams = new ArrayList<>();
        requiredParams.add("eventStartDate");

        checkRequiredParams(requiredParams, queryParams);

        return apiClient.searchFestival(queryParams);
    }

    public ResponseBodyDto searchStay(Map<String, String> queryParams) {

        return apiClient.searchStay(queryParams);
    }

    public ResponseBodyDto searchDetail(Map<String, String> queryParams) {

        requiredParams = new ArrayList<>();
        requiredParams.add("contentId");
        requiredParams.add("contentTypeId");

        checkRequiredParams(requiredParams, queryParams);

        queryParams.put("defaultYN", "Y");
        queryParams.put("firstImageYN", "Y");
        queryParams.put("areacodeYN", "Y");
        queryParams.put("catcodeYN", "Y");
        queryParams.put("addrinfoYN", "Y");
        queryParams.put("mapinfoYN", "Y");
        queryParams.put("overviewYN", "Y");

        return apiClient.searchDetail(queryParams);
    }

    public ResponseBodyDto searchArea(Map<String, String> queryParams) {

        return apiClient.searchArea(queryParams);
    }

    private static void checkRequiredParams(List<String> requiredParams, Map<String, String> param) {
        for (String requiredParam : requiredParams) {
            if (!param.containsKey(requiredParam)) {
                throw new NoRequiredParamException();
            }
        }
    }
}
