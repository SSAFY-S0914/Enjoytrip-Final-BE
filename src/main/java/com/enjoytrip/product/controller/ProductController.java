package com.enjoytrip.product.controller;

import com.enjoytrip.product.dto.AreaCodeDto;
import com.enjoytrip.product.dto.CategoryDto;
import com.enjoytrip.product.entity.AreaCode;
import com.enjoytrip.product.entity.Category;
import com.enjoytrip.product.mapper.AreaCodeMapper;
import com.enjoytrip.product.mapper.CategoryMapper;
import com.enjoytrip.product.service.ProductService;
import com.enjoytrip.utils.dtoUtils.SingleResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final CategoryMapper categoryMapper;
    private final AreaCodeMapper areaCodeMapper;

    @GetMapping("/categories")
    public ResponseEntity<SingleResponseDto<List<CategoryDto.Get>>> findAllCategories() {
        List<Category> categoryList = productService.findAllCategories();
        List<CategoryDto.Get> categoryDtoList = categoryMapper.categoryListToGetRequest(categoryList);
        return ResponseEntity.ok(new SingleResponseDto<>(categoryDtoList));
    }

    @GetMapping("/areacodes")
    public ResponseEntity<SingleResponseDto<List<AreaCodeDto.Get>>> findAllAreaCodes() {
        List<AreaCode> areaCodeList = productService.findAllAreaCodes();
        List<AreaCodeDto.Get> areaCodeDtoList = areaCodeMapper.areaCodeToGetRequest(areaCodeList);
        System.out.println(areaCodeDtoList);
        return ResponseEntity.ok(new SingleResponseDto<>(areaCodeDtoList));
    }
}
