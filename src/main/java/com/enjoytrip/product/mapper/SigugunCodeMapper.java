package com.enjoytrip.product.mapper;

import com.enjoytrip.product.dto.SigugunCodeDto;
import com.enjoytrip.product.entity.SigugunCode;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SigugunCodeMapper {

    SigugunCodeDto.Get sigugunCodeToGetRequest(SigugunCode sigugunCode);
}
