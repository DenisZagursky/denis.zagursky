package com.netcracker.zagursky.dao.impl;

import com.netcracker.zagursky.dao.GenericDao;
import com.netcracker.zagursky.exceptions.BdException;
import com.netcracker.zagursky.service.EntityManagerService;

import javax.persistence.*;
import java.util.List;


public abstract class GenericDaoImpl<T, ID> implements GenericDao<T, ID> {
    // public static final String QUERY_FIND_ALL="from :custname";
    protected EntityManager entityManager = EntityManagerService.getInstance();
    protected Class<T> type;
    protected EntityTransaction transaction = entityManager.getTransaction();

    protected final void setClass(Class<T> classToSet) {
        this.type = classToSet;
    }

    public T findById(ID id) throws Exception {
        try {
            return entityManager.find(type, id);
        } catch (IllegalArgumentException ex) {
            throw new BdException("not valid arguments", ex);
        }


    }

    public List<T> findAll() throws Exception {
        try {
            return entityManager.createQuery("from " + type.getName())
                    .getResultList();
        } catch (IllegalArgumentException ex) {
            throw new BdException("not valid arguments", ex);
        }

    }

    public T persist(T entity) throws Exception {
        try {
            transaction.begin();
            entityManager.persist(entity);
            transaction.commit();
            return entity;

        } catch (IllegalStateException ex) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new BdException("wrong transaction status", ex);
        } catch (EntityExistsException | IllegalArgumentException | TransactionRequiredException ex) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new BdException("wrong persist object", ex);

        } catch (RollbackException ex) {
            transaction.rollback();
            throw new BdException(" the commit fails");
        }


    }

    public void delete(T entity) throws Exception {
        try {
            transaction.begin();
            entityManager.remove(entity);
            transaction.commit();
        } catch (IllegalStateException ex) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new BdException("wrong transaction status", ex);
        } catch (IllegalArgumentException | TransactionRequiredException ex) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new BdException("wrong delete object", ex);

        } catch (RollbackException ex) {
            transaction.rollback();
            throw new BdException(" the commit fails");
        }


    }

    public void deleteById(ID id) throws Exception {
        try {
            T objectForRemove = entityManager.find(type, id);
            transaction.begin();
            entityManager.remove(objectForRemove);
            transaction.commit();
        } catch (IllegalStateException ex) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new BdException("wrong transaction status", ex);
        } catch (IllegalArgumentException | TransactionRequiredException ex) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new BdException("wrong delete object", ex);

        } catch (RollbackException ex) {
            transaction.rollback();
            throw new BdException(" the commit fails");
        }
    }

    public T update(T entity) throws Exception {
        try {
            transaction.begin();
            entityManager.merge(entity);
            transaction.commit();
            return entity;
        } catch (IllegalStateException ex) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new BdException("wrong transaction status", ex);
        } catch (IllegalArgumentException | TransactionRequiredException ex) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new BdException("wrong delete object", ex);

        } catch (RollbackException ex) {
            transaction.rollback();
            throw new BdException(" the commit fails");
        }
    }


}
