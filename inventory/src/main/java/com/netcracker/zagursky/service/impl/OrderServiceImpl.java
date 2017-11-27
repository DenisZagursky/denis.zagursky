package com.netcracker.zagursky.service.impl;

import com.netcracker.zagursky.dao.OrderDao;
import com.netcracker.zagursky.entity.Order;
import com.netcracker.zagursky.entity.OrderItem;
import com.netcracker.zagursky.exceptions.DbException;
import com.netcracker.zagursky.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Dzenyaa on 23.11.2017.
 */
@Transactional
@Service
public class OrderServiceImpl extends GenericServiceImpl<Order, Integer> implements OrderService {
    @Autowired
    private OrderDao orderDao;

    @Override
    @Transactional(readOnly = true)
    public List<OrderItem> getOrderItems(int id) throws DbException {

        return orderDao.getOrderItems(id);

    }


}
