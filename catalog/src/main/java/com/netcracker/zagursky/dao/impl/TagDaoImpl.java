package com.netcracker.zagursky.dao.impl;

import com.netcracker.zagursky.dao.TagDao;
import com.netcracker.zagursky.entity.Tag;
import com.netcracker.zagursky.exceptions.DbException;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by Dzenyaa on 14.11.2017.
 */
@Repository
@Transactional(rollbackOn = DbException.class)
public class TagDaoImpl extends GenericDaoImpl<Tag, Integer> implements TagDao {
    public static final String QUERY_FIND_BY_NAME = "SELECT c FROM Tag c WHERE c.name LIKE :custName";

    {
        setClass(Tag.class);
    }

    public Tag findByName(String name) throws DbException {
        try {
            return (Tag) entityManager.createQuery(QUERY_FIND_BY_NAME)
                    .setParameter("custName", name)
                    .setMaxResults(1)
                    .getSingleResult();
        } catch (IllegalArgumentException ex) {
            throw new DbException("not valid arguments", ex);
        }
    }
}
