package com.riderguru.rider_guru.notification.internal;

import com.riderguru.rider_guru.libs.GenericMapper;
import com.riderguru.rider_guru.notification.NotificationDto;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
interface NotificationMapper extends GenericMapper<Notification, NotificationDto> {
}
