package com.netcracker.zagursky.service;

import com.netcracker.zagursky.entity.Price;
import com.netcracker.zagursky.exceptions.DbException;

/**
 * Created by Dzenyaa on 27.11.2017.
 */
public interface PriceService extends GenericService<Price, Integer> {
    Price getByValue(double value) throws DbException;

}
