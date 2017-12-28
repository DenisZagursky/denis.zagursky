package com.netcracker.zagursky.service;

import com.netcracker.zagursky.exceptions.InventoryException;

import java.util.List;

/**
 * Created by Dzenyaa on 23.11.2017.
 */
public interface GenericService<T, ID> {
    T findById(ID id) throws InventoryException;

    List<T> findAll() throws InventoryException;

    T persist(T entity) throws InventoryException;

    void delete(ID id) throws InventoryException;


    T update(T entity) throws InventoryException;
}
