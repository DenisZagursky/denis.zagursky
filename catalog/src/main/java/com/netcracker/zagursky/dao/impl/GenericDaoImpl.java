package com.netcracker.zagursky.dao.impl;

import com.netcracker.zagursky.dao.GenericDao;

import java.util.List;

/**
 * Created by Dzenyaa on 14.11.2017.
 */
public abstract   class GenericDaoImpl<T,ID> implements GenericDao {


    public T findById(ID id) {

        return null;
    }

    public List<T> findAll() {
        return null;
    }

    public void save(Object entity) {

    }

    public void delete(Object entity) {

    }

    public void deleteById(Object o) {

    }

    public void update(Object entity) {

    }
}
