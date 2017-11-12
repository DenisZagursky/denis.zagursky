package com.netcracker.zagursky.dao;


import java.util.List;

public interface GenericDao<T, ID> {
    public T findById(ID id);

    public List<T> findAll();

    public void save(T entity);

    public void delete(T entity);

    public void deleteById(ID id);

    public void update(T entity);


}
