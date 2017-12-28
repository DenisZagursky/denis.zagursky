package com.netcracker.zagursky.controller;

import com.netcracker.zagursky.entity.Order;
import com.netcracker.zagursky.entity.OrderItem;
import com.netcracker.zagursky.exceptions.InventoryException;
import com.netcracker.zagursky.service.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Dzenyaa on 23.11.2017.
 */
@RestController
@RequestMapping("api/v1/orders")
public class OrderController {

    private final static Logger LOGGER = LogManager.getLogger("logger");

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity createOrder(@RequestBody Order order) throws InventoryException {
        orderService.persist(order);
        return new ResponseEntity(order, HttpStatus.CREATED);

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity updateOrder(@RequestBody Order order) throws InventoryException {
        orderService.update(order);
        return new ResponseEntity(order,HttpStatus.OK);

    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity getOrders() throws InventoryException {
        List<Order> orders = orderService.findAll();
        if (orders != null) {
            return new ResponseEntity(orders, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);

        }
    }


    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity getOrder(@PathVariable Integer id) throws InventoryException {

        Order order = orderService.findById(id);
        if (order != null) {
            return new ResponseEntity(order, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);

        }
    }

    @RequestMapping(value = "/email/", method = RequestMethod.GET)
    public ResponseEntity getCustomersOrders(@RequestParam String customerEmail) throws InventoryException {
        List<Order> orders = orderService.getCustomersOrders(customerEmail);
        if (orders != null) {
            return new ResponseEntity(orders, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);

        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteOrder(@PathVariable Integer id) throws InventoryException {
            orderService.delete(id);
            return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/OrderItem/{id}", method = RequestMethod.PUT)
    public ResponseEntity putOrderItem(@PathVariable Integer id, @RequestBody OrderItem orderItem) throws InventoryException {

        return new ResponseEntity(orderService.addOrderItem(id, orderItem), HttpStatus.OK);

    }

    @RequestMapping(value = "/status/{id}", method = RequestMethod.PUT)
    public ResponseEntity payOrder(@PathVariable Integer id) throws InventoryException {

        return new ResponseEntity(orderService.payOrder(id), HttpStatus.OK);

    }

    @RequestMapping(value = "/OrderItem/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteOrderItem(@PathVariable Integer id, @RequestBody OrderItem orderItem) throws InventoryException {
        return new ResponseEntity(orderService.removeOrderItem(id, orderItem), HttpStatus.OK);

    }



    @ExceptionHandler(InventoryException.class)
    public ResponseEntity handleDbException(InventoryException e) {
        LOGGER.error("ExceptionHandler found:",e);
        return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
    }

}
