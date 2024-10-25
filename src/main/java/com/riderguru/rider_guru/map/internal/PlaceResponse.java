package com.riderguru.rider_guru.map.internal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.riderguru.rider_guru.map.PlaceDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
class PlaceResponse {
    @JsonProperty("predictions")
    private List<PlaceDto> places;
}
