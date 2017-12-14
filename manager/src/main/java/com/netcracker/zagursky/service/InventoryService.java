package com.netcracker.zagursky.service;

import com.netcracker.zagursky.entity.inventory.Order;
import com.netcracker.zagursky.entity.inventory.OrderItem;
import com.netcracker.zagursky.exception.ClientException;

import java.util.List;

/**
 * Created by Dzenyaa on 09.12.2017.
 */
public interface InventoryService {
    Order createOrder(Order order) throws ClientException;
    Order addOrderItem(Integer id, OrderItem orderItem) throws ClientException;
    List<Order> getCustomerOrders(String email) throws ClientException;
    List<Order> getPaidCustomerOrders(String email) throws ClientException;
    List<Order> getUnpaidCusomerOrders(String email) throws ClientException;
    Double  getTotalPriceOfCustomerOrders(String email) throws ClientException;
    Order payForOrder(Order order) throws ClientException;
    List<Order> getOrderByStatus(Boolean status) throws ClientException;
    Order getOrder(Integer id) throws ClientException;
    Order updateOrder(Order order) throws ClientException;
    void deleteOrder(Integer id) throws ClientException;
}
