package com.netcracker.zagursky.service;

import com.netcracker.zagursky.exceptions.DbException;

import java.util.List;

/**
 * Created by Dzenyaa on 23.11.2017.
 */
public interface GenericService<T, ID> {
    T findById(ID id) throws DbException;

    List<T> findAll() throws DbException;

    T persist(T entity) throws DbException;

    void delete(ID id) throws DbException;

    T update(T entity) throws DbException;
}
