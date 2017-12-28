package com.netcracker.zagursky.dao.impl;

import com.netcracker.zagursky.dao.GenericDao;
import com.netcracker.zagursky.exceptions.CatalogException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class GenericDaoImpl<T, ID> implements GenericDao<T, ID> {
    @PersistenceContext
    protected EntityManager entityManager;
    protected Class<T> type;


    protected final void setClass(Class<T> classToSet) {
        this.type = classToSet;
    }

    public T findById(ID id) throws CatalogException {
        try {
            return entityManager.find(type, id);
        } catch (Exception ex) {
            throw new CatalogException("not valid arguments", ex);
        }


    }

    public List<T> findAll() throws CatalogException {
        try {
            return entityManager.createQuery("from " + type.getName())
                    .getResultList();
        } catch (Exception ex) {
            throw new CatalogException("not valid arguments", ex);
        }

    }

    public T persist(T entity) throws CatalogException {
        try {

            entityManager.persist(entity);
            return entity;

        } catch (Exception ex) {
            throw new CatalogException("wrong persist object", ex);
        }
    }


    public void delete(ID id) throws CatalogException {
        try {
            T objectForRemove = entityManager.find(type, id);
            entityManager.remove(objectForRemove);
        } catch (Exception ex) {
            throw new CatalogException("wrong delete object", ex);
        }
    }

    public T update(T entity) throws CatalogException {
        try {
            entityManager.merge(entity);
            return entity;
        } catch (Exception ex) {
            throw new CatalogException("wrong update object", ex);
        }
    }


}
