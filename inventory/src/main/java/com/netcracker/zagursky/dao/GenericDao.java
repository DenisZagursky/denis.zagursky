package com.netcracker.zagursky.dao;


import com.netcracker.zagursky.exceptions.InventoryException;

import java.util.List;

public interface GenericDao<T, ID> {
    T findById(ID id) throws InventoryException;

    List<T> findAll() throws InventoryException;

    T persist(T entity) throws InventoryException;

    void delete(ID id) throws InventoryException;

    T update(T entity) throws InventoryException;


}
