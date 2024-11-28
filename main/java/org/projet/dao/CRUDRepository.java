package main.java.org.projet.dao;

import java.util.List;

public interface CRUDRepository<T> {
    void save(T entity);

    void update(T entity);

    void delete(T entity);

    List<T> findAll();
}
