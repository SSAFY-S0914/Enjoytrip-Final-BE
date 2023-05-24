package com.enjoytrip.product.controller;

import com.enjoytrip.product.dto.ResponseBodyDto;
import com.enjoytrip.product.service.ProductSearchService;
import com.enjoytrip.utils.dtoUtils.SingleResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product/search")
public class ProductSearchController {

    private final ProductSearchService productSearchService;

    @GetMapping("/location")
    public ResponseEntity<SingleResponseDto<ResponseBodyDto>> searchLocation(@RequestParam Map<String, String> queryParams) {
        ResponseBodyDto results = productSearchService.searchLocation(queryParams);
        return ResponseEntity.ok(new SingleResponseDto<>(results));
    }

    @GetMapping("/keyword")
    public ResponseEntity<SingleResponseDto<ResponseBodyDto>> searchKeyword(@RequestParam Map<String, String> queryParams) {
        ResponseBodyDto results = productSearchService.searchKeyword(queryParams);
        return ResponseEntity.ok(new SingleResponseDto<>(results));
    }

    @GetMapping("/festival")
    public ResponseEntity<SingleResponseDto<ResponseBodyDto>> searchFestival(@RequestParam Map<String, String> queryParams) {
        ResponseBodyDto results = productSearchService.searchFestival(queryParams);
        return ResponseEntity.ok(new SingleResponseDto<>(results));
    }

    @GetMapping("/stay")
    public ResponseEntity<SingleResponseDto<ResponseBodyDto>> searchStay(@RequestParam Map<String, String> queryParams) {
        ResponseBodyDto results = productSearchService.searchStay(queryParams);
        return ResponseEntity.ok(new SingleResponseDto<>(results));
    }

    @GetMapping("/detail")
    public ResponseEntity<SingleResponseDto<ResponseBodyDto>> searchDetail(@RequestParam Map<String, String> queryParams) {
        ResponseBodyDto results = productSearchService.searchDetail(queryParams);
        return ResponseEntity.ok(new SingleResponseDto<>(results));
    }

    @GetMapping("/area")
    public ResponseEntity<SingleResponseDto<ResponseBodyDto>> searchArea(@RequestParam Map<String, String> queryParams) {
        ResponseBodyDto results = productSearchService.searchArea(queryParams);
        return ResponseEntity.ok(new SingleResponseDto<>(results));
    }

}
