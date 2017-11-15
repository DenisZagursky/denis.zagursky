package com.netcracker.zagursky.dao.impl;

import com.netcracker.zagursky.dao.PriceDao;
import com.netcracker.zagursky.entity.Price;


public class PriceDaoImpl extends GenericDaoImpl<Price, Integer> implements PriceDao {
    {
        setClass(Price.class);
    }

    public Price getByValue(double value) {
        return (Price) entityManager.createQuery(
                "SELECT c FROM Price c WHERE c.price=:custName")
                .setParameter("custName", value)
                .setMaxResults(1)
                .getSingleResult();
    }
}
