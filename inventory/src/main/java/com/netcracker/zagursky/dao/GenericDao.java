package com.netcracker.zagursky.dao;


import com.netcracker.zagursky.exceptions.DbException;

import java.util.List;

public interface GenericDao<T, ID> {
    T findById(ID id) throws DbException;

    List<T> findAll() throws DbException;

    T persist(T entity) throws DbException;

    void delete(ID id) throws DbException;

    T update(T entity) throws DbException;


}
