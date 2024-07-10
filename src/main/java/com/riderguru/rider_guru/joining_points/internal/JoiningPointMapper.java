package com.riderguru.rider_guru.joining_points.internal;

import com.riderguru.rider_guru.joining_points.JoiningPointsDto;
import com.riderguru.rider_guru.libs.GenericMapper;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
interface JoiningPointMapper extends GenericMapper<JoiningPoint, JoiningPointsDto> {
}
