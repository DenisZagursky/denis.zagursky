package com.netcracker.zagursky.service.impl;

import com.netcracker.zagursky.dao.GenericDao;
import com.netcracker.zagursky.exceptions.InventoryException;
import com.netcracker.zagursky.service.GenericService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    private final static Logger LOGGER = LogManager.getLogger("logger");
    @Autowired
    private GenericDao<T, ID> genericDao;

    @Override
    @Transactional(readOnly = true)
    public T findById(ID id) throws InventoryException {
        LOGGER.debug("find by id. Start transaction");
        T entity = genericDao.findById(id);
        LOGGER.debug("find by id. End transaction. Entity:" + entity);
        return entity;
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> findAll() throws InventoryException {
        LOGGER.debug("find all. Start transaction");
        List<T> entities = genericDao.findAll();
        LOGGER.debug("find all. End transaction. Entity:" + entities);
        return entities;
    }

    @Override
    public T persist(T entity) throws InventoryException {
        LOGGER.debug("persist entity. Start transaction");
        entity = genericDao.persist(entity);
        LOGGER.debug("persist entity. End transaction. Entity:" + entity.toString());
        return entity;
    }


    @Override
    public void delete(ID id) throws InventoryException {
        LOGGER.debug("delete entity. Start transaction");
        genericDao.delete(id);
        LOGGER.debug("delete entity. End transaction.");

    }

    @Override
    public T update(T entity) throws InventoryException {
        LOGGER.debug("update entity. Start transaction");
        entity = genericDao.update(entity);
        LOGGER.debug("update entity. End transaction. Entity:" + entity.toString());
        return entity;
    }
}
