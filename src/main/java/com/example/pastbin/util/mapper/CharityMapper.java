package com.example.pastbin.util.mapper;

import com.example.pastbin.entity.Charity;
import com.example.pastbin.util.dto.CharityDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface CharityMapper {
    CharityDto toEntityDto(Charity charity);
    Charity toEntity(CharityDto charityDto);
}