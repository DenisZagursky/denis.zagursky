package com.netcracker.zagursky.dao.impl;

import com.netcracker.zagursky.dao.TagDao;
import com.netcracker.zagursky.entity.Offer;
import com.netcracker.zagursky.entity.Tag;
import com.netcracker.zagursky.exceptions.CatalogException;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Dzenyaa on 14.11.2017.
 */
@Repository
public class TagDaoImpl extends GenericDaoImpl<Tag, Integer> implements TagDao {
    public static final String QUERY_FIND_BY_NAME = "SELECT c FROM Tag c WHERE c.name LIKE :custName";
    public static final String QUERY_GET_OFFERS = "select c from Offer c join c.tags  d where d.id=:custName";

    {
        setClass(Tag.class);
    }

    public Tag findByName(String name) throws CatalogException {
        try {
            return (Tag) entityManager.createQuery(QUERY_FIND_BY_NAME)
                    .setParameter("custName", name)
                    .setMaxResults(1)
                    .getSingleResult();
        } catch (IllegalArgumentException ex) {
            throw new CatalogException("not valid arguments", ex);
        }
    }

    public List<Offer> getOffers(Integer id) throws CatalogException {
        try {
            return entityManager.createQuery(QUERY_GET_OFFERS)
                    .setParameter("custName", id)
                    .getResultList();
        } catch (IllegalArgumentException ex) {
            throw new CatalogException("not valid arguments", ex);
        }
    }
}
