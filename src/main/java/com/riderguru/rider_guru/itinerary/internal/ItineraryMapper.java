package com.riderguru.rider_guru.itinerary.internal;

import com.riderguru.rider_guru.itinerary.ItineraryDto;
import com.riderguru.rider_guru.libs.GenericMapper;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface ItineraryMapper extends GenericMapper<Itinerary, ItineraryDto> {
}
