package com.riderguru.rider_guru.trips.internal;

import com.riderguru.rider_guru.libs.GenericMapper;
import com.riderguru.rider_guru.trips.TripDto;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
interface TripMapper extends GenericMapper<Trip, TripDto> {
}
