package com.netcracker.zagursky.client;

import com.netcracker.zagursky.entity.inventory.Order;
import com.netcracker.zagursky.entity.inventory.OrderItem;
import com.netcracker.zagursky.exception.ClientException;


import java.util.List;

/**
 * Created by Dzenyaa on 08.12.2017.
 */
public interface InventoryClient {
     Order getOrderById(Integer id) throws ClientException;
     Order postOrder(Order order) throws ClientException;
     Order addOrderItem(Integer id, OrderItem orderItem) throws ClientException;
     List<Order> getCustumerOrder(String email)throws ClientException;
     Order updateOrder(Order order) throws ClientException;
     void deleteOrder(Integer id) throws ClientException;
     List<Order> getOrders() throws ClientException;
}
