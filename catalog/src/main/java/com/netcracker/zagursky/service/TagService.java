package com.netcracker.zagursky.service;

import com.netcracker.zagursky.entity.Tag;
import com.netcracker.zagursky.exceptions.DbException;

/**
 * Created by Dzenyaa on 27.11.2017.
 */
public interface TagService extends GenericService<Tag, Integer> {
    Tag findByName(String name) throws DbException;

}
