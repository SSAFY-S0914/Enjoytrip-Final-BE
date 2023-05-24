package com.enjoytrip.product.service;

import com.enjoytrip.product.entity.AreaCode;
import com.enjoytrip.product.entity.Category;
import com.enjoytrip.product.repository.AreaCodeRepository;
import com.enjoytrip.product.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final CategoryRepository categoryRepository;
    private final AreaCodeRepository areaCodeRepository;

    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    public List<AreaCode> findAllAreaCodes() {
        return areaCodeRepository.findAll();
    }
}
