package org.example.springdatam1.services;

import java.util.List;
import java.util.Optional;

public interface CrudService<T, ID> {
    void save(T entity);
    List<T> findAll();
    Optional<T> findById(ID id);
    void update(T entity);
    void delete(ID id);
}
