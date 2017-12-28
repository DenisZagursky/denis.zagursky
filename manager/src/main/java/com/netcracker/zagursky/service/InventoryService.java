package com.netcracker.zagursky.service;

import com.netcracker.zagursky.entity.catalog.Offer;
import com.netcracker.zagursky.entity.inventory.Order;
import com.netcracker.zagursky.entity.inventory.Status;
import com.netcracker.zagursky.exception.ManagerException;

import java.util.List;

/**
 * Created by Dzenyaa on 09.12.2017.
 */
public interface InventoryService {
    Order createOrder(String mail) throws ManagerException;

    Order addOrderItem(Integer idOrder, Offer offer) throws ManagerException;

    List<Order> getCustomerOrders(String email) throws ManagerException;

    List<Order> getPaidCustomerOrders(String email) throws ManagerException;

    List<Order> getUnpaidCusomerOrders(String email) throws ManagerException;

    Double getTotalPriceOfCustomerOrders(String email) throws ManagerException;

    Order payForOrder(Integer id) throws ManagerException;

    List<Order> getOrderByStatus(Status status) throws ManagerException;

    Order getOrder(Integer id) throws ManagerException;

    Order updateOrder(Order order) throws ManagerException;

    void deleteOrder(Integer id) throws ManagerException;
}
