package com.netcracker.zagursky.service.impl;

import com.netcracker.zagursky.dao.GenericDao;
import com.netcracker.zagursky.exceptions.DbException;
import com.netcracker.zagursky.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Dzenyaa on 23.11.2017.
 */
@Transactional
@Service
public abstract class GenericServiceImpl<T, ID> implements GenericService<T, ID> {
    @Autowired
    private GenericDao<T, ID> genericDao;

    @Override
    @Transactional(readOnly = true)
    public T findById(ID id) throws DbException {
        return genericDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> findAll() throws DbException {
        return genericDao.findAll();
    }

    @Override
    public T persist(T entity) throws DbException {
        return genericDao.persist(entity);

    }


    @Override
    public void delete(ID id) throws DbException {
        genericDao.delete(id);
    }

    @Override
    public T update(T entity) throws DbException {
        return genericDao.update(entity);
    }
}
