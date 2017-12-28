package com.netcracker.zagursky.dao.impl;

import com.netcracker.zagursky.dao.GenericDao;
import com.netcracker.zagursky.exceptions.InventoryException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public abstract class GenericDaoImpl<T, ID> implements GenericDao<T, ID> {
    @PersistenceContext
    protected EntityManager entityManager;
    protected Class<T> type;


    public T findById(ID id) throws InventoryException {
        try {
             T entity=entityManager.find(type, id);
             return entity;
        } catch (Exception ex) {
            throw new InventoryException("Not valid arguments", ex);
        }


    }

    public List<T> findAll() throws InventoryException {
        try {
            return entityManager.createQuery("from " + type.getName())
                    .getResultList();
        } catch (Exception ex) {
            throw new InventoryException("not valid arguments", ex);
        }

    }

    public T persist(T entity) throws InventoryException {
        try {

            entityManager.persist(entity);
            return entity;

        } catch (Exception ex) {
            throw new InventoryException("wrong persist object", ex);
        }
    }


    public void delete(ID id) throws InventoryException {
        try {
            T objectForRemove = entityManager.find(type, id);
            entityManager.remove(objectForRemove);
        } catch (Exception ex) {
            throw new InventoryException("wrong delete object", ex);
        }
    }

    public T update(T entity) throws InventoryException {
        try {
            entityManager.merge(entity);
            return entity;
        } catch (Exception ex) {
            throw new InventoryException("wrong update object", ex);
        }
    }


}
