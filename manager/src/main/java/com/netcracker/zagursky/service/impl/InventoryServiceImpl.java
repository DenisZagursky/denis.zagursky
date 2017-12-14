package com.netcracker.zagursky.service.impl;

import com.netcracker.zagursky.client.InventoryClient;
import com.netcracker.zagursky.entity.inventory.Order;
import com.netcracker.zagursky.entity.inventory.OrderItem;
import com.netcracker.zagursky.exception.ClientException;
import com.netcracker.zagursky.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dzenyaa on 09.12.2017.
 */
@Service
public class InventoryServiceImpl implements InventoryService {
    @Autowired
    private InventoryClient inventoryClient;

    @Override
    public Order createOrder(Order order) throws ClientException {
        return inventoryClient.postOrder(order);
    }

    @Override
    public Order addOrderItem(Integer id, OrderItem orderItem) throws ClientException {
        return inventoryClient.addOrderItem(id, orderItem);
    }

    @Override
    public List<Order> getCustomerOrders(String email) throws ClientException {
        return inventoryClient.getCustumerOrder(email);
    }

    @Override
    public List<Order> getPaidCustomerOrders(String email) throws ClientException {
        List<Order> orders = inventoryClient.getCustumerOrder(email);
        List<Order> result = new ArrayList<>();
        for (Order order : orders) {
            if (order.getSignOfThePayment()) {
                result.add(order);
            }
        }
        return result;
    }

    @Override
    public List<Order> getUnpaidCusomerOrders(String email) throws ClientException {
        List<Order> orders = inventoryClient.getCustumerOrder(email);
        List<Order> result = new ArrayList<>();
        for (Order order : orders) {
            if (order.getSignOfThePayment()!=true) {
                result.add(order);
            }
        }
        return result;
    }

    @Override
    public Double getTotalPriceOfCustomerOrders(String email) throws ClientException {
        List<Order> orders = inventoryClient.getCustumerOrder(email);
        Double result=0.0;
        for (Order order : orders) {
            result+=order.getTotalPrice();
        }
        return result;
    }

    @Override
    public Order payForOrder(Order order) throws ClientException {
        order.setSignOfThePayment(true);
        return inventoryClient.updateOrder(order);
    }

    @Override
    public List<Order> getOrderByStatus(Boolean status) throws ClientException {
        List<Order> orders = inventoryClient.getOrders();
        List<Order> result = new ArrayList<>();
        for (Order order : orders) {
            if (order.getSignOfThePayment()==status) {
                result.add(order);
            }
        }
        return result;
    }

    @Override
    public Order getOrder(Integer id) throws ClientException {
        return inventoryClient.getOrderById(id);
    }

    @Override
    public Order updateOrder(Order order) throws ClientException {
        return inventoryClient.updateOrder(order);
    }

    @Override
    public void deleteOrder(Integer id) throws ClientException {
         inventoryClient.deleteOrder(id);
    }
}
