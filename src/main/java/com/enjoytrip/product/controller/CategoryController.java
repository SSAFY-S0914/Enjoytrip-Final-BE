package com.enjoytrip.product.controller;

import com.enjoytrip.product.dto.CategoryDto;
import com.enjoytrip.product.entity.Category;
import com.enjoytrip.product.mapper.CategoryMapper;
import com.enjoytrip.product.service.CategoryService;
import com.enjoytrip.utils.dtoUtils.SingleResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @GetMapping("/categories")
    public ResponseEntity findAllCategories() {
        List<Category> categoryList = categoryService.findAllCategories();
        List<CategoryDto.Get> categoryDtoList = categoryMapper.categoryListToGetRequest(categoryList);
        return ResponseEntity.ok(new SingleResponseDto<>(categoryDtoList));
    }
}
