package com.netcracker.zagursky.controller;

import com.netcracker.zagursky.entity.catalog.Category;
import com.netcracker.zagursky.entity.catalog.Offer;
import com.netcracker.zagursky.entity.catalog.Tag;
import com.netcracker.zagursky.exception.ClientException;
import com.netcracker.zagursky.service.CatalogService;
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

    @Autowired
    private CatalogService catalogService;

    @RequestMapping(value = "/offers/category/{categoryName}", method = RequestMethod.GET)
    public ResponseEntity getOffersFromCategory(@PathVariable String categoryName) throws ClientException {
        List<Offer> offers = catalogService.getOffersByCategory(categoryName);
        if (offers != null) {
            return new ResponseEntity(offers, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);

        }

    }

    @RequestMapping(value = "/offers/tags", method = RequestMethod.GET)
    public ResponseEntity getOffersFromTags(@RequestParam List<String> tags) throws ClientException {
        List<Offer> offers = catalogService.getOffersByTags(tags);
        if (offers != null) {
            return new ResponseEntity(offers, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);

        }

    }

    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public ResponseEntity getCategories() throws ClientException {
        List<Category> categories = catalogService.getCategories();
        if (categories != null) {
            return new ResponseEntity(categories, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);

        }

    }

    @RequestMapping(value = "/tags", method = RequestMethod.GET)
    public ResponseEntity getTags() throws ClientException {
        List<Tag> tags = catalogService.getTags();
        if (tags != null) {
            return new ResponseEntity(tags, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);

        }

    }

    @RequestMapping(value = "/offers/price/{belowPrice}/{uponPrice}", method = RequestMethod.GET)
    public ResponseEntity getOffersByPrice(@PathVariable double belowPrice, @PathVariable double uponPrice) throws ClientException {
        List<Offer> offers = catalogService.getOffersInPriceRange(belowPrice, uponPrice);
        if (offers != null) {
            return new ResponseEntity(offers, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);

        }

    }

    @ExceptionHandler(ClientException.class)
    public ResponseEntity handleDbException(ClientException e) {
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
