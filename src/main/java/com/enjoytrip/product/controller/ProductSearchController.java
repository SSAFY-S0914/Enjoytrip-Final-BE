package com.enjoytrip.product.controller;

import com.enjoytrip.product.dto.ResponseBodyDto;
import com.enjoytrip.product.service.ProductSearchService;
import com.enjoytrip.utils.dtoUtils.SingleResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity searchLocation(@RequestParam Map<String, String> queryParams) {
        ResponseBodyDto results = productSearchService.searchLocation(queryParams);
        return new ResponseEntity(new SingleResponseDto<>(results), HttpStatus.OK);
    }

    @GetMapping("/keyword")
    public ResponseEntity searchKeyword(@RequestParam Map<String, String> queryParams) {
        ResponseBodyDto results = productSearchService.searchKeyword(queryParams);
        return new ResponseEntity(new SingleResponseDto<>(results), HttpStatus.OK);
    }

    @GetMapping("/festival")
    public ResponseEntity searchFestival(@RequestParam Map<String, String> queryParams) {
        ResponseBodyDto results = productSearchService.searchFestival(queryParams);
        return new ResponseEntity(new SingleResponseDto<>(results), HttpStatus.OK);
    }

    @GetMapping("/stay")
    public ResponseEntity searchStay(@RequestParam Map<String, String> queryParams) {
        ResponseBodyDto results = productSearchService.searchStay(queryParams);
        return new ResponseEntity(new SingleResponseDto<>(results), HttpStatus.OK);
    }

    @GetMapping("/area")
    public ResponseEntity searchArea(@RequestParam Map<String, String> queryParams) {
        ResponseBodyDto results = productSearchService.searchArea(queryParams);
        return new ResponseEntity(new SingleResponseDto<>(results), HttpStatus.OK);
    }

}
