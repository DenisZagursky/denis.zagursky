package com.netcracker.zagursky.controller;

import com.netcracker.zagursky.entity.catalog.Category;
import com.netcracker.zagursky.entity.catalog.Offer;
import com.netcracker.zagursky.entity.catalog.OffersFilter;
import com.netcracker.zagursky.entity.catalog.Tag;
import com.netcracker.zagursky.exception.ManagerException;
import com.netcracker.zagursky.service.CatalogService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Dzenyaa on 11.12.2017.
 */
@RestController
@RequestMapping("api/v1/catalog")
public class CatalogController {

    private static final Logger LOGGER = LogManager.getLogger("logger");

    @Autowired
    private CatalogService catalogService;

    @RequestMapping(value = "/offers/category/{categoryName}", method = RequestMethod.GET)
    public ResponseEntity getOffersFromCategory(@PathVariable String categoryName) throws ManagerException {
        List<Offer> offers = catalogService.getOffersByCategory(categoryName);
        if (offers != null) {
            return new ResponseEntity(offers, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);

        }

    }

    @RequestMapping(value = "/offers/tags", method = RequestMethod.GET)
    public ResponseEntity getOffersFromTags(@RequestParam List<String> tags) throws ManagerException {
        List<Offer> offers = catalogService.getOffersByTags(tags);
        if (offers != null) {
            return new ResponseEntity(offers, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);

        }

    }
    @RequestMapping(value = "/offers/filter", method = RequestMethod.GET)
    public ResponseEntity getOffersByFilter(@ModelAttribute OffersFilter filter) throws ManagerException {
        List<Offer> offers = catalogService.getOffersByFilter(filter);
        if (offers != null) {
            return new ResponseEntity(offers, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);

        }

    }

    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public ResponseEntity getCategories() throws ManagerException {
        List<Category> categories = catalogService.getCategories();
        if (categories != null) {
            return new ResponseEntity(categories, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);

        }

    }

    @RequestMapping(value = "/tags", method = RequestMethod.GET)
    public ResponseEntity getTags() throws ManagerException {
        List<Tag> tags = catalogService.getTags();
        if (tags != null) {
            return new ResponseEntity(tags, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);

        }

    }

    @RequestMapping(value = "/offers/price/{belowPrice}/{uponPrice}", method = RequestMethod.GET)
    public ResponseEntity getOffersByPrice(@PathVariable double belowPrice, @PathVariable double uponPrice) throws ManagerException {
        List<Offer> offers = catalogService.getOffersInPriceRange(belowPrice, uponPrice);
        if (offers != null) {
            return new ResponseEntity(offers, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);

        }

    }

    @ExceptionHandler(ManagerException.class)
    public ResponseEntity handleDbException(ManagerException e) {
        LOGGER.error(e);
        return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
    }

}
