package com.enjoytrip.product.mapper;

import com.enjoytrip.product.dto.AreaCodeDto;
import com.enjoytrip.product.entity.AreaCode;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AreaCodeMapper {

    AreaCodeDto.Get areaCodeToGetRequest(AreaCode areaCode);

    List<AreaCodeDto.Get> areaCodeToGetRequest(List<AreaCode> areaCode);
}
