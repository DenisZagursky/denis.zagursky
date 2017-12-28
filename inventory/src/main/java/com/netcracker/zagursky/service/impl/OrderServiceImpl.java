package com.netcracker.zagursky.service.impl;

import com.netcracker.zagursky.dao.OrderDao;
import com.netcracker.zagursky.entity.Order;
import com.netcracker.zagursky.entity.OrderItem;
import com.netcracker.zagursky.entity.Status;
import com.netcracker.zagursky.exceptions.InventoryException;
import com.netcracker.zagursky.service.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    private static final Logger LOGGER = LogManager.getLogger("logger");
    @Autowired
    private OrderDao orderDao;

    @Override
    public Order removeOrderItem(int idOrder, OrderItem orderItem) throws InventoryException {
        LOGGER.debug("Remove order item. Start transaction");
        Order order = orderDao.findById(idOrder);
        if (order.getStatus() != Status.CONSERVED) {
            LOGGER.info("wrong status of order. status:" + order.getStatus());
            throw new InventoryException("U cant change order with status " + order.getStatus());
        }
        order.removeOrderItem(orderItem);
        if (order.getOrderItems() == null) {
            order.setStatus(Status.CREATED);
        }
        orderDao.update(order);
        LOGGER.debug("Remove order item. end transaction. result:" + order.toString());
        return order;
    }

    @Override
    public Order addOrderItem(int idOrder, OrderItem orderItem) throws InventoryException {
        LOGGER.debug("Add order item. Start transaction");
        Order order = orderDao.findById(idOrder);
        if (order == null) {
            LOGGER.info("cant Find Order");
            throw new InventoryException("cant Find Order");
        }
        order.addOrderItem(orderItem);
        order.setStatus(Status.CONSERVED);
        orderDao.update(order);
        LOGGER.debug("Add order item. End transaction. order:" + order.toString());
        return order;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Order> getCustomersOrders(String customerEmail) throws InventoryException {
        LOGGER.debug("get Customers Orders. Start transaction");
        List<Order> orders = orderDao.findAll();
        if (orders == null) {
            LOGGER.info("cant Find Orders");
            return orders;
        }
        List customersOrders = new ArrayList<Order>();
        for (Order order : orders) {
            if (customerEmail.equals(order.getCostumersEmail())) {
                customersOrders.add(order);
            }
        }
        LOGGER.debug("get Customers Orders. end transaction. orders:" + customersOrders.toString());
        return customersOrders;
    }

    @Override
    public Order payOrder(Integer id) throws InventoryException {
        LOGGER.debug("pay order. Start transaction");
        Order order = orderDao.findById(id);
        if (order == null) {
            LOGGER.info("cant find Order");
            throw new InventoryException("cant Find Order");
        }
        if (order.getStatus() != Status.CONSERVED) {
            LOGGER.info("cant pay order with status:" + order.getStatus());
            throw new InventoryException("U cant pay order with Status:" + order.getStatus());
        }
        order.setStatus(Status.PAID);
        order = orderDao.update(order);
        LOGGER.debug("pay order.End transaction. order:" + order.toString());
        return order;
    }

    @Override
    public Order persist(Order entity) throws InventoryException {
        LOGGER.debug("Order persist.Start transaction");
        entity.setStatus(Status.CREATED);
        entity = super.persist(entity);
        LOGGER.debug("Order persist. End Transaction");
        return entity;
    }

    @Override
    public Order update(Order entity) throws InventoryException {
        LOGGER.debug("Order update. Start transaction");
        if (entity.getStatus() != Status.CONSERVED) {
            LOGGER.info("U cant change order with status " + entity.getStatus());
            throw new InventoryException("U cant change order with status " + entity.getStatus());
        }
        entity = super.update(entity);
        LOGGER.debug("Order update. End transaction");
        return entity;
    }

    @Override
    public void delete(Integer id) throws InventoryException {
        LOGGER.debug("Order delete. start Transaction");
        Order order = orderDao.findById(id);
        if (order == null) {
            LOGGER.info("cant find Order");
            throw new InventoryException("cant Find Order");
        }
        switch (order.getStatus()) {
            case CREATED:
                super.delete(id);
                LOGGER.debug("Order delete.End Transaction");
                break;
            case PAID:
                LOGGER.info("U cant delete order with status Paid");
                throw new InventoryException("U cant delete order with status Paid");
            case CONSERVED:
                order.setStatus(Status.CANCELED);
                orderDao.update(order);
                LOGGER.debug("Order delete.End Transaction");
                break;
            case CANCELED:
                LOGGER.info("U cant delete order with status Canceled");
                throw new InventoryException("U cant delete order with status Canceled");
        }
    }
}
