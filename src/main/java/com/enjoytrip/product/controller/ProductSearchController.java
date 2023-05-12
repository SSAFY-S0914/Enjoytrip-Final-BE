package com.enjoytrip.product.controller;

import com.enjoytrip.product.service.ProductSearchService;
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
    public ResponseEntity searchLocation(@RequestParam Map<String, String> queryParams) {

    }

    @GetMapping("/keyword")
    public ResponseEntity searchKeyword(@RequestParam Map<String, String> queryParams) {
    }

    @GetMapping("/festival")
    public ResponseEntity searchFestival(@RequestParam Map<String, String> queryParams) {
    }

    @GetMapping("/stay")
    public ResponseEntity searchStay(@RequestParam Map<String, String> queryParams) {
    }

    @GetMapping("/area")
    public ResponseEntity searchArea(@RequestParam Map<String, String> queryParams) {
    }

}
