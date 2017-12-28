package com.netcracker.zagursky.service.impl;

import com.netcracker.zagursky.dao.PriceDao;
import com.netcracker.zagursky.entity.Price;
import com.netcracker.zagursky.exceptions.CatalogException;
import com.netcracker.zagursky.service.PriceService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Dzenyaa on 27.11.2017.
 */
@Transactional
@Service
public class PriceServiceImpl extends GenericServiceImpl<Price, Integer> implements PriceService {

    private final static Logger LOGGER = LogManager.getLogger("logger");

    @Autowired
    private PriceDao priceDao;

    @Transactional(readOnly = true)
    @Override
    public Price getByValue(double value) throws CatalogException {
        LOGGER.debug("Get by Value. Start transaction.");
        Price price= priceDao.getByValue(value);
        LOGGER.debug("Get by Value.End transaction. Price:"+price);
        return price;
    }
}
