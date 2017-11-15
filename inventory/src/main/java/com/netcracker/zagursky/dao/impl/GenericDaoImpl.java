package com.netcracker.zagursky.dao.impl;

import com.netcracker.zagursky.dao.GenericDao;
import com.netcracker.zagursky.service.EntityManagerService;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;


public abstract class GenericDaoImpl<T, ID> implements GenericDao<T, ID> {

    protected EntityManager entityManager = EntityManagerService.getInstance();
    protected Class<T> type;
    EntityTransaction transaction = entityManager.getTransaction();


    public final void setClass(Class<T> classToSet) {
        this.type = classToSet;
    }

    public T findById(ID id) {

        return entityManager.find(type, id);

    }

    public List<T> findAll() {

        return entityManager.createQuery("from " + type.getName())
                .getResultList();
    }

    public void persist(T entity) {
        transaction.begin();
        entityManager.persist(entity);
        transaction.commit();


    }

    public void delete(T entity) {
        transaction.begin();
        entityManager.remove(entity);
        transaction.commit();


    }

    public void deleteById(ID id) {
        transaction.begin();
        T objectForRemove = entityManager.find(type, id);
        entityManager.remove(objectForRemove);
        transaction.commit();
    }

    public void update(T entity) {
        transaction.begin();
        entityManager.merge(entity);
        transaction.commit();
    }


}
