package com.netcracker.zagursky.service;

import com.netcracker.zagursky.entity.Order;
import com.netcracker.zagursky.entity.OrderItem;
import com.netcracker.zagursky.exceptions.DbException;

import java.util.List;

/**
 * Created by Dzenyaa on 23.11.2017.
 */
public interface OrderService extends GenericService<Order, Integer> {
    Order addOrderItem(int idOrder, OrderItem orderItem) throws DbException;

    Order removeOrderItem(int idOrder, OrderItem orderItem) throws DbException;

    List<Order> getCustomersOrders(String customerEmail) throws DbException;
}
