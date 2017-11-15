package com.netcracker.zagursky.dao.impl;

import com.netcracker.zagursky.dao.OrderDao;
import com.netcracker.zagursky.entity.Order;
import com.netcracker.zagursky.entity.OrderItem;

import java.util.List;

/**
 * Created by Dzenyaa on 15.11.2017.
 */
public class OrderDaoImpl extends GenericDaoImpl<Order, Integer> implements OrderDao {
    {type=Order.class;}
    public List<OrderItem> getOrderItems(int id) {
        return entityManager.createQuery(
                "  SELECT p FROM Order c join c.orderItems p WHERE c.id =:custName")
                .setParameter("custName", id)
                .getResultList();
    }
}
