package com.netcracker.zagursky.dao.impl;

import com.netcracker.zagursky.dao.PriceDao;
import com.netcracker.zagursky.entity.Price;
import com.netcracker.zagursky.exceptions.CatalogException;
import org.springframework.stereotype.Repository;

@Repository
public class PriceDaoImpl extends GenericDaoImpl<Price, Integer> implements PriceDao {
    public static final String QUERY_GET_BY_VALUE = "SELECT c FROM Price c WHERE c.price=:custName";

    {
        setClass(Price.class);
    }

    public Price getByValue(double value) throws CatalogException {
        try {
            return (Price) entityManager.createQuery(QUERY_GET_BY_VALUE)
                    .setParameter("custName", value)
                    .setMaxResults(1)
                    .getSingleResult();
        } catch (Exception ex) {
            throw new CatalogException("not valid arguments", ex);
        }
    }
}
