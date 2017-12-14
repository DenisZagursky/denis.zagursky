package com.netcracker.zagursky.controller;

import com.netcracker.zagursky.entity.inventory.Order;
import com.netcracker.zagursky.entity.inventory.OrderItem;
import com.netcracker.zagursky.exception.ClientException;
import com.netcracker.zagursky.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Dzenyaa on 09.12.2017.
 */
@RestController
@RequestMapping("api/v1/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;


    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity createOrder(@RequestBody Order order) throws ClientException{

        inventoryService.createOrder(order);
        return new ResponseEntity(order, HttpStatus.CREATED);

    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity getOrder(@PathVariable Integer id) throws ClientException{

        Order order = inventoryService.getOrder(id);
        if (order != null) {
            return new ResponseEntity(order, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);

        }
    }

    @RequestMapping(value = "/email/{customerEmail}", method = RequestMethod.GET)
    public ResponseEntity getCustomersOrders(@PathVariable String customerEmail) throws ClientException {
        List<Order> orders = inventoryService.getCustomerOrders(customerEmail);
        if (orders != null) {
            return new ResponseEntity(orders, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);

        }
    }

    @RequestMapping(value = "/email/paid{customerEmail}", method = RequestMethod.GET)
    public ResponseEntity getPaidCustomersOrders(@PathVariable String customerEmail) throws ClientException {
        List<Order> orders = inventoryService.getPaidCustomerOrders(customerEmail);
        if (orders != null) {
            return new ResponseEntity(orders, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);

        }
    }

    @RequestMapping(value = "/email/unpaid{customerEmail}", method = RequestMethod.GET)
    public ResponseEntity getUnpaidCustomersOrders(@PathVariable String customerEmail) throws ClientException {
        List<Order> orders = inventoryService.getUnpaidCusomerOrders(customerEmail);
        if (orders != null) {
            return new ResponseEntity(orders, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);

        }
    }

    @RequestMapping(value = "/email/price/{customerEmail}", method = RequestMethod.GET)
    public ResponseEntity getPriceOfCustomersOrders(@PathVariable String customerEmail) throws ClientException {
        Double price = inventoryService.getTotalPriceOfCustomerOrders(customerEmail);
        if (price!= null) {
            return new ResponseEntity(price, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);

        }
    }

    @RequestMapping(value = "/status/{status}", method = RequestMethod.GET)
    public ResponseEntity getOrdersByStatus(@PathVariable Boolean status) throws ClientException {
        List<Order> orders = inventoryService.getOrderByStatus(status);
        if (orders != null) {
            return new ResponseEntity(orders, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);

        }
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteOrder(@PathVariable Integer id) throws ClientException {

        Order order = inventoryService.getOrder(id);
        if (order != null) {
            inventoryService.deleteOrder(id);
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);

        }
    }

    @RequestMapping(value = "/addOrderItem/{id}", method = RequestMethod.PUT)
    public ResponseEntity putOrderItem(@PathVariable Integer id, @RequestBody OrderItem orderItem) throws ClientException {

        return new ResponseEntity(inventoryService.addOrderItem(id, orderItem), HttpStatus.OK);

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity updateOrder(@RequestBody Order order) throws ClientException {
        inventoryService.updateOrder(order);
        return new ResponseEntity(order,HttpStatus.OK);

    }

    @RequestMapping(value = "/payOrder/{id}", method = RequestMethod.PUT)
    public ResponseEntity payOrder(@RequestBody Order order) throws ClientException {
        inventoryService.payForOrder(order);
        return new ResponseEntity(order,HttpStatus.OK);

    }


    @ExceptionHandler(ClientException.class)
    public ResponseEntity handleDbException(ClientException e) {
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
