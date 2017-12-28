package com.netcracker.zagursky.service;

import com.netcracker.zagursky.entity.Order;
import com.netcracker.zagursky.entity.OrderItem;
import com.netcracker.zagursky.exceptions.InventoryException;

import java.util.List;

/**
 * Created by Dzenyaa on 23.11.2017.
 */
public interface OrderService extends GenericService<Order, Integer> {
    Order addOrderItem(int idOrder, OrderItem orderItem) throws InventoryException;

    Order removeOrderItem(int idOrder, OrderItem orderItem) throws InventoryException;

    List<Order> getCustomersOrders(String customerEmail) throws InventoryException;
    Order payOrder(Integer id) throws InventoryException;
}
