package com.netcracker.zagursky.dao;


import java.util.List;

public interface GenericDao<T, ID> {
    T findById(ID id) throws Exception;

    List<T> findAll() throws Exception;

    T persist(T entity) throws Exception;

    void delete(T entity) throws Exception;

    void deleteById(ID id) throws Exception;

    T update(T entity) throws Exception;


}
