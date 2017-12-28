package com.netcracker.zagursky.dao;


import com.netcracker.zagursky.exceptions.CatalogException;

import java.util.List;

public interface GenericDao<T, ID> {
    T findById(ID id) throws CatalogException;

    List<T> findAll() throws CatalogException;

    T persist(T entity) throws CatalogException;

    void delete(ID id) throws CatalogException;

    T update(T entity) throws CatalogException;


}
