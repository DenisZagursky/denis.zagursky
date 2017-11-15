package com.netcracker.zagursky.dao;


import java.util.List;

public interface GenericDao<T, ID> {
     T findById(ID id);

     List<T> findAll();

     void persist(T entity);

     void delete(T entity);

    void deleteById(ID id);

    void update(T entity);


}
