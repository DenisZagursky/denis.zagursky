package com.netcracker.zagursky.dao.impl;

import com.netcracker.zagursky.dao.GenericDao;
import com.netcracker.zagursky.exceptions.DbException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional(rollbackOn = DbException.class)
public class GenericDaoImpl<T, ID> implements GenericDao<T, ID> {
    @PersistenceContext
    protected EntityManager entityManager;
    protected Class<T> type;


    protected final void setClass(Class<T> classToSet) {
        this.type = classToSet;
    }

    public T findById(ID id) throws DbException {
        try {
            return entityManager.find(type, id);
        } catch (Exception ex) {
            throw new DbException("not valid arguments", ex);
        }


    }

    public List<T> findAll() throws DbException {
        try {
            return entityManager.createQuery("from " + type.getName())
                    .getResultList();
        } catch (Exception ex) {
            throw new DbException("not valid arguments", ex);
        }

    }

    public T persist(T entity) throws DbException {
        try {

            entityManager.persist(entity);
            return entity;

        } catch (Exception ex) {
            throw new DbException("wrong persist object", ex);
        }
    }

    public void delete(T entity) throws DbException {
        try {
            entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
        } catch (Exception ex) {
            throw new DbException("wrong delete object", ex);
        }


    }

    public void deleteById(ID id) throws DbException {
        try {
            T objectForRemove = entityManager.find(type, id);
            entityManager.remove(objectForRemove);
        } catch (Exception ex) {
            throw new DbException("wrong delete object", ex);
        }
    }

    public T update(T entity) throws DbException {
        try {
            entityManager.merge(entity);
            return entity;
        } catch (Exception ex) {
            throw new DbException("wrong update object", ex);
        }
    }


}
