package com.riderguru.rider_guru.libs;

public interface GenericMapper<E, D> {
    D toDto(E e);
    E toEntity(D d);
}
