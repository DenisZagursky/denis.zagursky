package com.netcracker.zagursky.controller;

import com.netcracker.zagursky.entity.Order;
import com.netcracker.zagursky.entity.OrderItem;
import com.netcracker.zagursky.exceptions.DbException;
import com.netcracker.zagursky.service.OrderService;
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

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity createOrder(@RequestBody Order order) throws DbException {

        orderService.persist(order);
        return new ResponseEntity(order, HttpStatus.CREATED);

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity updateOrder(@RequestBody Order order) throws DbException {
        orderService.update(order);
        return new ResponseEntity(HttpStatus.OK);

    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity getOrders() throws DbException {
        List<Order> orders = orderService.findAll();
        if (orders != null) {
            return new ResponseEntity(orders, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);

        }
    }


    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity getOrder(@PathVariable Integer id) throws DbException {

        Order order = orderService.findById(id);
        if (order != null) {
            return new ResponseEntity(order, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);

        }
    }

    @RequestMapping(value = "/email/{customerEmail}", method = RequestMethod.GET)
    public ResponseEntity getCustomersOrders(@PathVariable String customerEmail) throws DbException {
        List<Order> orders = orderService.getCustomersOrders(customerEmail);
        if (orders != null) {
            return new ResponseEntity(orders, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);

        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteOrder(@PathVariable Integer id) throws DbException {

        Order order = orderService.findById(id);
        if (order != null) {
            orderService.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);

        }
    }

    @RequestMapping(value = "/{id}/{orderItem}", method = RequestMethod.PUT)
    public ResponseEntity putOrderItem(@PathVariable Integer id, @RequestBody OrderItem orderItem) throws DbException {

        return new ResponseEntity(orderService.addOrderItem(id, orderItem), HttpStatus.OK);

    }

    @RequestMapping(value = "/{id}/{orderItem}", method = RequestMethod.DELETE)
    public ResponseEntity deleteOrderItem(@PathVariable Integer id, @RequestBody OrderItem orderItem) throws DbException {
        return new ResponseEntity(orderService.removeOrderItem(id, orderItem), HttpStatus.OK);

    }

    @ExceptionHandler(DbException.class)
    public ResponseEntity handleDbException(DbException e) {
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
