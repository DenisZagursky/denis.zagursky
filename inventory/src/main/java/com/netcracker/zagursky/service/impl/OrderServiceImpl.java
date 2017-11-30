package com.netcracker.zagursky.service.impl;

import com.netcracker.zagursky.dao.OrderDao;
import com.netcracker.zagursky.entity.Order;
import com.netcracker.zagursky.entity.OrderItem;
import com.netcracker.zagursky.exceptions.DbException;
import com.netcracker.zagursky.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    public Order removeOrderItem(int idOrder, OrderItem orderItem) throws DbException {
        Order order = orderDao.findById(idOrder);
        order.removeOrderItem(orderItem);
        orderDao.update(order);
        return order;
    }

    @Override
    public Order addOrderItem(int idOrder, OrderItem orderItem) throws DbException {

        Order order = orderDao.findById(idOrder);
        order.addOrderItem(orderItem);
        orderDao.update(order);
        return order;
    }

    @Override
    public List<Order> getCustomersOrders(String customerEmail) throws DbException {
        List<Order> orders = orderDao.findAll();
        List customersOrders = new ArrayList<Order>();
        for (Order order : orders) {
            if (order.getCostumersEmail().equals(customerEmail)) {
                customersOrders.add(order);
            }
        }
        return customersOrders;
    }
}
