package com.netcracker.zagursky.service.impl;

import com.netcracker.zagursky.dao.TagDao;
import com.netcracker.zagursky.entity.Tag;
import com.netcracker.zagursky.exceptions.DbException;
import com.netcracker.zagursky.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Dzenyaa on 27.11.2017.
 */
@Transactional
@Service
public class TagServiceImpl extends GenericServiceImpl<Tag, Integer> implements TagService {

    @Autowired
    private TagDao tagDao;

    @Transactional(readOnly = true)
    @Override
    public Tag findByName(String name) throws DbException {
        return tagDao.findByName(name);
    }
}
