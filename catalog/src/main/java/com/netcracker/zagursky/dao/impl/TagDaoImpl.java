package com.netcracker.zagursky.dao.impl;

import com.netcracker.zagursky.dao.TagDao;
import com.netcracker.zagursky.entity.Tag;

/**
 * Created by Dzenyaa on 14.11.2017.
 */
public class TagDaoImpl extends GenericDaoImpl<Tag, Integer> implements TagDao {
    {
        setClass(Tag.class);
    }

    public Tag findByName(String name) {
        return (Tag) entityManager.createQuery(
                "SELECT c FROM Tag c WHERE c.name LIKE :custName")
                .setParameter("custName", name)
                .setMaxResults(1)
                .getSingleResult();
    }
}
