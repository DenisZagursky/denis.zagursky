package com.netcracker.zagursky.controller;

import com.netcracker.zagursky.entity.catalog.Offer;
import com.netcracker.zagursky.entity.inventory.Order;
import com.netcracker.zagursky.entity.inventory.Status;
import com.netcracker.zagursky.exception.ManagerException;
import com.netcracker.zagursky.service.CatalogService;
import com.netcracker.zagursky.service.InventoryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    private static final Logger LOGGER = LogManager.getLogger("logger");

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private CatalogService catalogService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity createOrder(@RequestBody String mail) throws ManagerException {

        return new ResponseEntity(inventoryService.createOrder(mail),HttpStatus.CREATED);


    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity getOrder(@PathVariable Integer id) throws ManagerException {

        Order order = inventoryService.getOrder(id);
        if (order != null) {
            return new ResponseEntity(order, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);

        }
    }

    @RequestMapping(value = "/email/", method = RequestMethod.GET)
    public ResponseEntity getCustomersOrders(@RequestParam String customerEmail) throws ManagerException {
        List<Order> orders = inventoryService.getCustomerOrders(customerEmail);
        if (orders != null) {
            return new ResponseEntity(orders, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);

        }
    }

    @RequestMapping(value = "/email/paid", method = RequestMethod.GET)
    public ResponseEntity getPaidCustomersOrders(@RequestParam String customerEmail) throws ManagerException {
        List<Order> orders = inventoryService.getPaidCustomerOrders(customerEmail);
        if (orders != null) {
            return new ResponseEntity(orders, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);

        }
    }

    @RequestMapping(value = "/email/unpaid", method = RequestMethod.GET)
    public ResponseEntity getUnpaidCustomersOrders(@RequestParam String customerEmail) throws ManagerException {
        List<Order> orders = inventoryService.getUnpaidCusomerOrders(customerEmail);
        if (orders != null) {
            return new ResponseEntity(orders, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);

        }
    }

    @RequestMapping(value = "/email/price", method = RequestMethod.GET)
    public ResponseEntity getPriceOfCustomersOrders(@RequestParam String customerEmail) throws ManagerException {
        Double price = inventoryService.getTotalPriceOfCustomerOrders(customerEmail);
        if (price != null) {
            return new ResponseEntity(price, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);

        }
    }

    @RequestMapping(value = "/status/{status}", method = RequestMethod.GET)
    public ResponseEntity getOrdersByStatus(@PathVariable Status status) throws ManagerException {
        List<Order> orders = inventoryService.getOrderByStatus(status);
        if (orders != null) {
            return new ResponseEntity(orders, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);

        }
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteOrder(@PathVariable Integer id) throws ManagerException {

            inventoryService.deleteOrder(id);
            return new ResponseEntity(HttpStatus.OK);

    }

    @RequestMapping(value = "/addOrderItem/{idOrder}/{idOffer}", method = RequestMethod.PUT)
    public ResponseEntity putOrderItem(@PathVariable Integer idOrder, @PathVariable Integer idOffer) throws ManagerException {
        Offer offer = catalogService.getOfferById(idOffer);
        if (offer != null) {
            return new ResponseEntity(inventoryService.addOrderItem(idOrder, offer), HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);


        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity updateOrder(@RequestBody Order order) throws ManagerException {
        inventoryService.updateOrder(order);
        return new ResponseEntity(order, HttpStatus.OK);

    }

    @RequestMapping(value = "/payOrder/{id}", method = RequestMethod.PUT)
    public ResponseEntity payOrder(Integer id) throws ManagerException {

        return new ResponseEntity(inventoryService.payForOrder(id), HttpStatus.OK);

    }


    @ExceptionHandler(ManagerException.class)
    public ResponseEntity handleException(ManagerException e) {
        LOGGER.error(e);
        return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
    }

}
