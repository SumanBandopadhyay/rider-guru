package com.riderguru.rider_guru.libs;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface GenericService<E> {
    E save(E d);
    Optional<E> getById(Long id);
    void delete(Long id);
    List<E> getAll();
    List<E> query(Map<String, String> params);
}
