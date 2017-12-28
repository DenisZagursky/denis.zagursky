package com.netcracker.zagursky.dao.impl;

import com.netcracker.zagursky.dao.OfferDao;
import com.netcracker.zagursky.entity.Offer;
import com.netcracker.zagursky.entity.OffersFilter;
import com.netcracker.zagursky.exceptions.CatalogException;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;


@Repository
public class OfferDaoImpl extends GenericDaoImpl<Offer, Integer> implements OfferDao {
    private static final String QUERY_FIND_BY_NAME = "SELECT c FROM Offer c WHERE c.name LIKE :custName";
    private static final String QUERY_FIND_BY_TAG = "SELECT c FROM Offer c join c.tags p WHERE p.name LIKE :custName";
    private static final String QUERY_FIND_BY_CATEGORY = "SELECT c FROM Offer c join c.category p WHERE p.name LIKE :custName and c.status=true";
    private static final String QUERY_GET_AVAILABLE_OFFERS = "SELECT c FROM Offer c where c.status=true";
    private static final String QUERY_GET_IN_PRICE_RANGE = "select c from Offer c  join c.price p where p.price between :custName1 and :custName2 and c.status=true";

    {
        setClass(Offer.class);
    }

    public Offer findByName(String name) throws CatalogException {
        try {
            return (Offer) entityManager.createQuery(QUERY_FIND_BY_NAME)
                    .setParameter("custName", name)
                    .setMaxResults(1)
                    .getSingleResult();
        } catch (Exception ex) {
            throw new CatalogException("not valid arguments", ex);
        }

    }

    public List<Offer> findByTag(String tagName) throws CatalogException {
        try {
            return entityManager.createQuery(QUERY_FIND_BY_TAG)
                    .setParameter("custName", tagName)
                    .getResultList();
        } catch (Exception ex) {
            throw new CatalogException("not valid arguments", ex);
        }
    }


    public List<Offer> findByCategory(String categoryName) throws CatalogException {
        try {
            return entityManager.createQuery(QUERY_FIND_BY_CATEGORY)
                    .setParameter("custName", categoryName)
                    .getResultList();
        } catch (Exception ex) {
            throw new CatalogException("not valid arguments", ex);
        }
    }


    public List<Offer> getAvailableOffers() throws CatalogException {
        try {
            return entityManager.createQuery(QUERY_GET_AVAILABLE_OFFERS)
                    .getResultList();
        } catch (Exception ex) {
            throw new CatalogException("not valid arguments", ex);
        }
    }

    @Override
    public List<Offer> findOffersInRange(double belowPrice, double uponPrice) throws CatalogException {
        try {
            return entityManager.createQuery(QUERY_GET_IN_PRICE_RANGE)
                    .setParameter("custName1", belowPrice)
                    .setParameter("custName2", uponPrice)
                    .getResultList();
        } catch (Exception ex) {
            throw new CatalogException("not valid arguments", ex);
        }
    }

    @Override
    public List<Offer> findOffersByFilter(OffersFilter filter, String query) throws CatalogException {
        try {

            Query entityManagerQuery= entityManager.createQuery(query);
            if(filter.getTags().size()!=0)
            {
                entityManagerQuery.setParameter("names",filter.getTags());
            }
            List<Offer> offers=entityManagerQuery.getResultList();
            return offers;
        } catch (Exception ex){
            throw new CatalogException("not valid arguments",ex);
        }

    }


}
