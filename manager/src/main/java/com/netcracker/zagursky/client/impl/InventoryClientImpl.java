package com.netcracker.zagursky.client.impl;

import com.netcracker.zagursky.client.InventoryClient;
import com.netcracker.zagursky.entity.inventory.Order;
import com.netcracker.zagursky.entity.inventory.OrderItem;
import com.netcracker.zagursky.exception.ManagerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by Dzenyaa on 07.12.2017.
 */
@Component
public class InventoryClientImpl implements InventoryClient {

    @Autowired
    HttpHeaders headers;
    @Value("${endpoint.inventory}")
    private String PATH_TO_INVENTORY;
    @Autowired
    private RestTemplate restTemplate;

    public Order getOrderById(Integer id) throws ManagerException {
        try {
            ResponseEntity<Order> orderResponseEntity = restTemplate.getForEntity(PATH_TO_INVENTORY + "orders/" + id, Order.class);
            return orderResponseEntity.getBody();
        } catch (Exception e) {
            throw new ManagerException("wrong get Order", e);
        }
    }

    public Order postOrder(Order order) throws ManagerException {
        try {
            HttpEntity request = new HttpEntity(order, headers);
            ResponseEntity<Order> orderResponseEntity = restTemplate.postForEntity(PATH_TO_INVENTORY + "orders/",
                    request,
                    Order.class);
            return orderResponseEntity.getBody();
        } catch (Exception e) {
            throw new ManagerException("wrong post Order", e);
        }
    }

    @Override
    public Order addOrderItem(Integer id, OrderItem orderItem) throws ManagerException {
        ResponseEntity<Order> orderResponseEntity;
        try {
            HttpEntity request = new HttpEntity(orderItem, headers);
         orderResponseEntity = restTemplate.exchange(PATH_TO_INVENTORY + "orders/OrderItem/" + id,
                    HttpMethod.PUT,
                    request,
                    Order.class);
            return orderResponseEntity.getBody();
        } catch (Exception e) {
            throw new ManagerException("wrong add orderitem", e);
        }
    }

    @Override
    public List<Order> getCustumerOrder(String email) throws ManagerException {
        try {
            HttpEntity request = new HttpEntity( headers);
            ResponseEntity<List<Order>> orderResponseEntity = restTemplate.exchange(PATH_TO_INVENTORY + "orders/email/?customerEmail="+email,
                    HttpMethod.GET,
                    request,
                    new ParameterizedTypeReference<List<Order>>() {
                    });
            return orderResponseEntity.getBody();
        } catch (Exception e) {
            throw new ManagerException("wrong get custumers orders", e);
        }
    }

    @Override
    public Order updateOrder(Order order) throws ManagerException {
        try {
            HttpEntity request = new HttpEntity(order, headers);
            ResponseEntity<Order> orderResponseEntity = restTemplate.exchange(PATH_TO_INVENTORY + "orders/" + order.getId(), HttpMethod.PUT, request, Order.class);
            return orderResponseEntity.getBody();
        } catch (Exception e) {
            throw new ManagerException("wrong update order", e);
        }
    }

    @Override
    public Order payOrder(Integer id) throws ManagerException {
        try {
            HttpEntity request = new HttpEntity( headers);
            ResponseEntity<Order> orderResponseEntity = restTemplate.exchange(PATH_TO_INVENTORY + "orders/status/"+id, HttpMethod.PUT, request, Order.class);
            return orderResponseEntity.getBody();
        } catch (Exception e) {
            throw new ManagerException("wrong pay order", e);
        }
    }

    @Override
    public void deleteOrder(Integer id) throws ManagerException {

        try {
            HttpEntity request = new HttpEntity(headers);
            restTemplate.delete(PATH_TO_INVENTORY + "orders/" + id, HttpMethod.DELETE, request);
        } catch (Exception e) {
            throw new ManagerException("wrong delete order", e);
        }
    }

    @Override
    public List<Order> getOrders() throws ManagerException {
        try {
            HttpEntity request = new HttpEntity(headers);
            ResponseEntity<List<Order>> orderResponseEntity = restTemplate.exchange(PATH_TO_INVENTORY + "orders/",
                    HttpMethod.GET, request,
                    new ParameterizedTypeReference<List<Order>>() {
                    });
            return orderResponseEntity.getBody();
        } catch (Exception e) {
            throw new ManagerException("wrong get  orders", e);
        }
    }

}
