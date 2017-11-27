package com.netcracker.zagursky.service;

import com.netcracker.zagursky.entity.Order;
import com.netcracker.zagursky.entity.OrderItem;
import com.netcracker.zagursky.exceptions.DbException;

import java.util.List;

/**
 * Created by Dzenyaa on 23.11.2017.
 */
public interface OrderService extends GenericService<Order, Integer> {
    public List<OrderItem> getOrderItems(int id) throws DbException;

}
