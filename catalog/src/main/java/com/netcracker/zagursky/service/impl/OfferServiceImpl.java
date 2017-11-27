package com.netcracker.zagursky.service.impl;

import com.netcracker.zagursky.dao.OfferDao;
import com.netcracker.zagursky.entity.Offer;
import com.netcracker.zagursky.exceptions.DbException;
import com.netcracker.zagursky.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Dzenyaa on 27.11.2017.
 */
@Transactional
@Service
public class OfferServiceImpl extends GenericServiceImpl<Offer, Integer> implements OfferService {
    @Autowired
    private OfferDao offerDao;

    @Transactional(readOnly = true)
    @Override
    public Offer findByName(String name) throws DbException {
        return offerDao.findByName(name);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Offer> findByTag(String tagName) throws DbException {
        return offerDao.findByTag(tagName);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Offer> findByCategory(String categoryName) throws DbException {
        return offerDao.findByCategory(categoryName);
    }
}
