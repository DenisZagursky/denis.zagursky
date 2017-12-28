package com.netcracker.zagursky.service.impl;

import com.netcracker.zagursky.dao.TagDao;
import com.netcracker.zagursky.entity.Offer;
import com.netcracker.zagursky.entity.Tag;
import com.netcracker.zagursky.exceptions.CatalogException;
import com.netcracker.zagursky.service.TagService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Dzenyaa on 27.11.2017.
 */
@Transactional
@Service
public class TagServiceImpl extends GenericServiceImpl<Tag, Integer> implements TagService {

    private final static Logger LOGGER = LogManager.getLogger("logger");

    @Autowired
    private TagDao tagDao;

    @Transactional(readOnly = true)
    @Override
    public Tag findByName(String name) throws CatalogException {
        LOGGER.debug("Find by name. Start transaction");
        Tag tag= tagDao.findByName(name);
        LOGGER.debug("Find by name. End transaction. Tag:"+tag);
        return tag;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Offer> getOffers(Integer id) throws CatalogException {
        LOGGER.debug("Get offers. Start transaction");
        List<Offer>  offers=tagDao.getOffers(id);
        LOGGER.debug("Get offers. End transaction. Offers:"+offers);
        return offers;
    }
}
