package com.netcracker.zagursky.service;

import com.netcracker.zagursky.exceptions.CatalogException;

import java.util.List;

/**
 * Created by Dzenyaa on 23.11.2017.
 */
public interface GenericService<T, ID> {
    T findById(ID id) throws CatalogException;

    List<T> findAll() throws CatalogException;

    T persist(T entity) throws CatalogException;

    void delete(ID id) throws CatalogException;

    T update(T entity) throws CatalogException;
}
