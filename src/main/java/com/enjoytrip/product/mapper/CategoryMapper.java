//package com.enjoytrip.product.mapper;
//
//import com.enjoytrip.product.dto.CategoryDto;
//import com.enjoytrip.product.entity.Category;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//
//import java.util.List;
//
//@Mapper(componentModel = "spring")
//public interface CategoryMapper {
//
//    @Mapping(target = "contentTypeId", source = "category.id")
//    CategoryDto.Get categoryToGeRequest(Category category);
//
//    List<CategoryDto.Get> categoryListToGetRequest(List<Category> categoryList);
//
//}
