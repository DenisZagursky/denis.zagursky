package com.netcracker.zagursky.service.impl;

import com.netcracker.zagursky.dao.OfferDao;
import com.netcracker.zagursky.entity.*;
import com.netcracker.zagursky.exceptions.CatalogException;
import com.netcracker.zagursky.service.OfferService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dzenyaa on 27.11.2017.
 */
@Transactional
@Service
public class OfferServiceImpl extends GenericServiceImpl<Offer, Integer> implements OfferService {

    private final static Logger LOGGER = LogManager.getLogger("logger");

    @Autowired
    private OfferDao offerDao;

    @Transactional(readOnly = true)
    @Override
    public Offer findByName(String name) throws CatalogException {
        LOGGER.debug("Find by name. Start transaction.");
        Offer offer = offerDao.findByName(name);
        LOGGER.debug("Find by name. End transaction. offer:" + offer);
        return offer;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Offer> findByTags(List<String> tagsName) throws CatalogException {
        LOGGER.debug("Find by Tags. Start transaction");
        List<Offer> offers = offerDao.findByTag(tagsName.get(0));
        if (offers.size() == 0) {
            LOGGER.info("cant find offers");
            return null;
        }
        List<Offer> resultOffers = new ArrayList<>();
        for (Offer offer : offers) {
            List<String> offersTags = new ArrayList<>();
            for (Tag tag : offer.getTags()) {
                offersTags.add(tag.getName());
            }
            if (offersTags.containsAll(tagsName)) {
                resultOffers.add(offer);
            }
        }
        LOGGER.debug("Find By tags. End transaction. offers:" + resultOffers);
        return resultOffers;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Offer> findByCategory(String categoryName) throws CatalogException {
        LOGGER.debug("Find by category. Start transaction.");
        List<Offer> offers = offerDao.findByCategory(categoryName);
        LOGGER.debug("Find by category. End transaction. offers:" + offers);
        return offers;
    }

    @Override
    public void changeStatus(Offer offer) throws CatalogException {
        LOGGER.debug("Change Status. Start transaction");
        if (offer.getStatus()) {
            offer.setStatus(false);
        } else {
            offer.setStatus(true);
        }
        offer = offerDao.update(offer);
        LOGGER.debug("Change Status. End transaction. offer:" + offer);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Offer> getAvailableOffers() throws CatalogException {
        LOGGER.debug("Get available offers. Start transaction");
        List<Offer> offers = offerDao.getAvailableOffers();
        LOGGER.debug("Get available offers. End transaction. offers:" + offers);
        return offers;
    }

    @Override
    public Offer addTag(int id, Tag tag) throws CatalogException {
        LOGGER.debug("Add tag. Start transaction");
        Offer offer = offerDao.findById(id);
        if (offer == null) {
            LOGGER.info("cant find offer");
            throw new CatalogException("cant find offer");
        }
        offer.addTag(tag);
        offerDao.update(offer);
        LOGGER.debug("Add tag. End transaction. offer" + offer);
        return offer;
    }

    @Override
    public Offer removeTag(Integer id, Tag tag) throws CatalogException {
        LOGGER.debug("Remove tag. Start transaction");
        Offer offer = offerDao.findById(id);
        if (offer == null) {
            LOGGER.info("cant find offer");
            throw new CatalogException("cant find offer");
        }
        offer.removeTag(tag);
        offerDao.update(offer);
        LOGGER.debug("Remove tag. Start transaction. offer:" + offer);
        return offer;
    }

    @Override
    public Offer setCategory(Integer id, Category category) throws CatalogException {
        LOGGER.debug("Set Category. Start transaction");
        Offer offer = offerDao.findById(id);
        if (offer == null) {
            LOGGER.info("cant find offer");
            throw new CatalogException("cant find offer");
        }
        offer.setCategory(category);
        offerDao.update(offer);
        LOGGER.debug("Set Category. End transaction. offer" + offer);
        return offer;
    }

    @Override
    public Offer removeCategory(Integer id) throws CatalogException {
        LOGGER.debug("Remove Category. Start transaction");
        Offer offer = offerDao.findById(id);
        if (offer == null) {
            LOGGER.info("cant find offer");
            throw new CatalogException("cant find offer");
        }
        offer.setCategory(null);
        offerDao.update(offer);
        LOGGER.debug("Remove Category. End transaction.offer" + offer);
        return offer;
    }

    @Override
    public Offer setPrice(Integer id, Price price) throws CatalogException {
        LOGGER.debug("Set price. Start transaction");
        Offer offer = offerDao.findById(id);
        if (offer == null) {
            LOGGER.info("cant find offer");
            throw new CatalogException("cant find offer");
        }
        offer.setPrice(price);
        offerDao.update(offer);
        LOGGER.debug("Set price. End transaction. offer:" + offer);
        return offer;
    }

    @Override
    public Offer changePrice(Integer id, Integer price) throws CatalogException {
        LOGGER.debug("Change Price. Start transaction");
        Offer offer = offerDao.findById(id);
        if (offer == null) {
            LOGGER.info("cant find offer");
            throw new CatalogException("cant find offer");
        }
        offer.getPrice().setPrice(price);
        offerDao.update(offer);
        LOGGER.debug("Change price. End transaction. offer:" + offer);
        return offer;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Offer> findByPrice(double belowPrice, double uponPrice) throws CatalogException {
        LOGGER.debug("Find by Price. Start transaction");
        List<Offer> offers = offerDao.findOffersInRange(belowPrice, uponPrice);
        LOGGER.debug("Find by Price. End transaction. offers:" + offers);
        return offers;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Offer> findByFilter(OffersFilter filter) throws CatalogException {
        LOGGER.debug("Find By Filter. Start transaction.");
        StringBuilder selectPartOfQuery = new StringBuilder();
        StringBuilder wherePartOfQuery = new StringBuilder();
        selectPartOfQuery.append("SELECT DISTINCT offer FROM Offer offer");
        wherePartOfQuery.append(" WHERE offer.status=true");
        if (filter.getBelowPrice() != 0 || filter.getUponPrice() != 0) {
            selectPartOfQuery.append(" join offer.price price");
            if (filter.getBelowPrice() != 0) {
                wherePartOfQuery.append(" and price.price>=" + filter.getBelowPrice());
            }
            if (filter.getUponPrice() != 0) {
                wherePartOfQuery.append(" and price.price<=" + filter.getUponPrice());
            }
        }
        if (filter.getCategoryName() != null) {
            selectPartOfQuery.append(" join offer.category category");
            wherePartOfQuery.append(" and category.name LIKE '" + filter.getCategoryName()+"'");
        }
        if (filter.getTags().size() != 0) {
            selectPartOfQuery.append(" join offer.tags tags");
            wherePartOfQuery.append(" and   tags.name IN (:names)");
        }
        LOGGER.debug("Find By filter. Query:"+selectPartOfQuery.toString()+wherePartOfQuery.toString());
        List<Offer> offers=offerDao.findOffersByFilter(filter, selectPartOfQuery.toString() + wherePartOfQuery.toString());
        LOGGER.debug("Find By Filter. End transaction. Offers:"+offers);
        return offers;
    }
}
