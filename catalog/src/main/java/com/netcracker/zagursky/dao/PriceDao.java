package com.netcracker.zagursky.dao;

import com.netcracker.zagursky.entity.Price;
import com.netcracker.zagursky.exceptions.CatalogException;

/**
 * Created by Dzenyaa on 09.11.2017.
 */
public interface PriceDao extends GenericDao<Price, Integer> {
    Price getByValue(double value) throws CatalogException;
}
