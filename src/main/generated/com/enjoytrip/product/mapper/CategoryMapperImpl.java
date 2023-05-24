package com.enjoytrip.product.mapper;

import com.enjoytrip.product.dto.CategoryDto.Get;
import com.enjoytrip.product.entity.Category;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-24T09:36:04+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.7 (Azul Systems, Inc.)"
)
@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public Get categoryToGeRequest(Category category) {
        if ( category == null ) {
            return null;
        }

        Long contentTypeId = null;
        String name = null;

        contentTypeId = category.getId();
        name = category.getName();

        Get get = new Get( contentTypeId, name );

        return get;
    }

    @Override
    public List<Get> categoryListToGetRequest(List<Category> categoryList) {
        if ( categoryList == null ) {
            return null;
        }

        List<Get> list = new ArrayList<Get>( categoryList.size() );
        for ( Category category : categoryList ) {
            list.add( categoryToGeRequest( category ) );
        }

        return list;
    }
}
