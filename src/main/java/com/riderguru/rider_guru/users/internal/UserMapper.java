package com.riderguru.rider_guru.users.internal;

import com.riderguru.rider_guru.libs.GenericMapper;
import com.riderguru.rider_guru.users.UserDto;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
interface UserMapper extends GenericMapper<User, UserDto> {
}
