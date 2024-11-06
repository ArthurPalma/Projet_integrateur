package main.java.org.projet.dao;

import java.util.List;

public interface CRUDRepository <T>{
    void create(T entity);
    T findById(Long id);
    List<T> findAll();
    void update(T entity);
    void delete(T entity);
}
