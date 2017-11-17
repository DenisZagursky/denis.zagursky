package com.netcracker.zagursky.dao;

import com.netcracker.zagursky.entity.Tag;
import com.netcracker.zagursky.exceptions.DbException;

/**
 * Created by Dzenyaa on 09.11.2017.
 */
public interface TagDao extends GenericDao<Tag, Integer> {
    Tag findByName(String name) throws DbException;
}
