package ru.clevertec.repository;

import java.util.List;

public interface CrudRepository<T, K> {
    void save(T t);

    T getById(K id);

    void update(T t);

    void deleteById(K id);

    List<T> getAll();
}
