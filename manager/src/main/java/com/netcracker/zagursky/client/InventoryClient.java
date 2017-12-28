package com.netcracker.zagursky.client;

import com.netcracker.zagursky.entity.inventory.Order;
import com.netcracker.zagursky.entity.inventory.OrderItem;
import com.netcracker.zagursky.exception.ManagerException;

import java.util.List;

/**
 * Created by Dzenyaa on 08.12.2017.
 */
public interface InventoryClient {
    Order getOrderById(Integer id) throws ManagerException;

    Order postOrder(Order order) throws ManagerException;

    Order addOrderItem(Integer id, OrderItem orderItem) throws ManagerException;

    List<Order> getCustumerOrder(String email) throws ManagerException;

    Order updateOrder(Order order) throws ManagerException;
    Order payOrder( Integer id) throws ManagerException;

    void deleteOrder(Integer id) throws ManagerException;

    List<Order> getOrders() throws ManagerException;
}
