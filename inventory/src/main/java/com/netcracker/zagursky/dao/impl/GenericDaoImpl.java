package com.netcracker.zagursky.dao.impl;

import com.netcracker.zagursky.dao.GenericDao;
import com.netcracker.zagursky.exceptions.DbException;
import com.netcracker.zagursky.service.EntityManagerService;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;


public abstract class GenericDaoImpl<T, ID> implements GenericDao<T, ID> {


    protected EntityManager entityManager = EntityManagerService.getInstance();
    protected EntityTransaction transaction = entityManager.getTransaction();
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
            transaction.begin();
            entityManager.persist(entity);
            transaction.commit();
            return entity;

        } catch (Exception ex) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new DbException("wrong persist object", ex);
        }
    }

    public void delete(T entity) throws DbException {
        try {
            transaction.begin();
            entityManager.remove(entity);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new DbException("wrong delete object", ex);
        }


    }

    public void deleteById(ID id) throws DbException {
        try {
            T objectForRemove = entityManager.find(type, id);
            transaction.begin();
            entityManager.remove(objectForRemove);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new DbException("wrong delete object", ex);
        }
    }

    public T update(T entity) throws DbException {
        try {
            transaction.begin();
            entityManager.merge(entity);
            transaction.commit();
            return entity;
        } catch (Exception ex) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new DbException("wrong update object", ex);
        }
    }


}
