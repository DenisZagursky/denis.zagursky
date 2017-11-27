package com.netcracker.zagursky.dao.impl;

import com.netcracker.zagursky.dao.OrderDao;
import com.netcracker.zagursky.entity.Order;
import com.netcracker.zagursky.entity.OrderItem;
import com.netcracker.zagursky.exceptions.DbException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Dzenyaa on 15.11.2017.
 */

@Repository
//@Transactional(rollbackOn = DbException.class)
public class OrderDaoImpl extends GenericDaoImpl<Order, Integer> implements OrderDao {
    public static final String QUERY_FIND_ORDER_ITEMS = "  SELECT p FROM Order c join c.orderItems p WHERE c.id =:custName";

    {
        type = Order.class;
    }

    @Autowired
    public void setClass() {
        type = Order.class;
    }

    public List<OrderItem> getOrderItems(int id) throws DbException {
        try {
            return entityManager.createQuery(QUERY_FIND_ORDER_ITEMS)
                    .setParameter("custName", id)
                    .getResultList();
        } catch (Exception ex) {
            throw new DbException("not valid arguments", ex);
        }
    }
}
