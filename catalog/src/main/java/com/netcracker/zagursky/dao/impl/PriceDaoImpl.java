package com.netcracker.zagursky.dao.impl;

import com.netcracker.zagursky.dao.PriceDao;
import com.netcracker.zagursky.entity.Price;
import com.netcracker.zagursky.exceptions.DbException;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional(rollbackOn = DbException.class)
public class PriceDaoImpl extends GenericDaoImpl<Price, Integer> implements PriceDao {
    public static final String QUERY_GET_BY_VALUE = "SELECT c FROM Price c WHERE c.price=:custName";

    {
        setClass(Price.class);
    }

    public Price getByValue(double value) throws DbException {
        try {
            return (Price) entityManager.createQuery(QUERY_GET_BY_VALUE)
                    .setParameter("custName", value)
                    .setMaxResults(1)
                    .getSingleResult();
        } catch (Exception ex) {
            throw new DbException("not valid arguments", ex);
        }
    }
}
