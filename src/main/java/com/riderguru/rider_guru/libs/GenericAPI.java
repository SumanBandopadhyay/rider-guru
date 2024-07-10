package com.riderguru.rider_guru.libs;

import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface GenericAPI<D> {
    ResponseEntity<D> create(D d);
    ResponseEntity<D> getById(Long id);
    ResponseEntity<List<D>> getAll();
    ResponseEntity<D> delete(Long id);
    ResponseEntity<List<D>> query(Map<String, String> params);
}
