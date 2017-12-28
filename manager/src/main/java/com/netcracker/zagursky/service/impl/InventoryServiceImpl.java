package com.netcracker.zagursky.service.impl;

import com.netcracker.zagursky.client.InventoryClient;
import com.netcracker.zagursky.entity.catalog.Offer;
import com.netcracker.zagursky.entity.catalog.Tag;
import com.netcracker.zagursky.entity.inventory.Order;
import com.netcracker.zagursky.entity.inventory.OrderItem;
import com.netcracker.zagursky.entity.inventory.Status;
import com.netcracker.zagursky.exception.ManagerException;
import com.netcracker.zagursky.service.InventoryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by Dzenyaa on 09.12.2017.
 */
@Service
public class InventoryServiceImpl implements InventoryService {

    private static final Logger LOGGER = LogManager.getLogger("logger");
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    @Autowired
    private InventoryClient inventoryClient;

    @Override
    public Order createOrder(String mail) throws ManagerException {

        LOGGER.debug("Create Order. Start transaction");
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(mail);
        if (!matcher.matches()) {
            LOGGER.info("Wrong Email");
            throw new ManagerException("wrong email");
        }
        Order order = new Order();
        order.setCostumersEmail(mail);
        order.setName("order_For_" + mail);
        order.setDescription("ver.1.0");
        order = inventoryClient.postOrder(order);
        LOGGER.debug("Create Order. End transaction");
        return order;
    }

    @Override
    public Order addOrderItem(Integer idOrder, Offer offer) throws ManagerException {
        LOGGER.debug("Add orderItem. Start transaction");
        OrderItem orderItem = new OrderItem();
        orderItem.setName(offer.getName());
        orderItem.setCategory(offer.getCategory().getName());
        orderItem.setDescription(offer.getDescription());
        orderItem.setPrice(offer.getPrice().getPrice());
        for (Tag tag : offer.getTags()) {
            orderItem.addTag(tag.getName());
        }
        Order order= inventoryClient.addOrderItem(idOrder, orderItem);
        LOGGER.debug("Add orderItem. End transaction. Order:"+order);
        return order;
    }

    @Override
    public List<Order> getCustomerOrders(String email) throws ManagerException {
        LOGGER.debug("Get customer orders. Start transaction");
        List<Order> orders= inventoryClient.getCustumerOrder(email);
        LOGGER.debug("Get customers orders. End transaction. orders:"+orders);
        return orders;

    }

    @Override
    public List<Order> getPaidCustomerOrders(String email) throws ManagerException {
        LOGGER.debug("Get paid customer orders. Start transaction");
        List<Order> orders = inventoryClient.getCustumerOrder(email);
        if (orders == null) {
            return null;
        }
        List<Order> result = new ArrayList<>();
        for (Order order : orders) {
            if (order.getStatus() == Status.PAID) {
                result.add(order);
            }
        }
        LOGGER.debug("Get paid customers orders. End transaction. orders:"+result);
        return result;
    }

    @Override
    public List<Order> getUnpaidCusomerOrders(String email) throws ManagerException {
        LOGGER.debug("Get unpaid customer orders. Start transaction");
        List<Order> orders = inventoryClient.getCustumerOrder(email);
        if (orders == null) {
            return null;
        }
        List<Order> result = new ArrayList<>();
        for (Order order : orders) {
            if (order.getStatus() != Status.PAID) {
                result.add(order);
            }
        }
        LOGGER.debug("Get unpaid customers orders. End transaction. orders:"+result);
        return result;
    }

    @Override
    public Double getTotalPriceOfCustomerOrders(String email) throws ManagerException {
        LOGGER.debug("Get total price of customer orders. Start transaction");
        List<Order> orders = inventoryClient.getCustumerOrder(email);
        if (orders == null) {
            return null;
        }
        Double result = 0.0;
        for (Order order : orders) {
            result += order.getTotalPrice();
        }
        LOGGER.debug("Get total price of customer orders. End transaction. Price:"+result);
        return result;
    }

    @Override
    public Order payForOrder(Integer id) throws ManagerException {
        LOGGER.debug("Pay for order. Start transaction");
        Order order= inventoryClient.payOrder(id);
        LOGGER.debug("Pay for order. End transaction. Order:"+order);
        return order;
    }

    @Override
    public List<Order> getOrderByStatus(Status status) throws ManagerException {
        LOGGER.debug("Get order by status. Start transaction");
        List<Order> orders = inventoryClient.getOrders();
        if (orders == null) {
            return null;
        }
        List<Order> result = new ArrayList<>();
        for (Order order : orders) {
            if (order.getStatus() == status) {
                result.add(order);
            }
        }
        LOGGER.debug("Get order by status. End transaction. Orders:"+orders);
        return result;
    }

    @Override
    public Order getOrder(Integer id) throws ManagerException {
        LOGGER.debug("Get order by id. Start transaction");
        Order order= inventoryClient.getOrderById(id);
        LOGGER.debug("Get order by id. End transaction. Order:"+order);
        return order;
    }

    @Override
    public Order updateOrder(Order order) throws ManagerException {
        LOGGER.debug("Update order. Start transaction");
        order= inventoryClient.updateOrder(order);
        LOGGER.debug("Update order. End transaction. Order:"+order);
        return order;
    }

    @Override
    public void deleteOrder(Integer id) throws ManagerException {
        LOGGER.debug("Delete order.Start transaction");
        inventoryClient.deleteOrder(id);
        LOGGER.debug("Delete order End transaction");

    }
}
