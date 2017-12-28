package com.netcracker.zagursky.dao;

import com.netcracker.zagursky.entity.Order;
import com.netcracker.zagursky.entity.OrderItem;
import com.netcracker.zagursky.exceptions.InventoryException;

import java.util.List;

public interface OrderDao extends GenericDao<Order, Integer> {
    public List<OrderItem> getOrderItems(int id) throws InventoryException;

}
