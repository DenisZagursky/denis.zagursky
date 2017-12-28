package com.netcracker.zagursky.controller;

import com.netcracker.zagursky.entity.*;
import com.netcracker.zagursky.entity.dto.OfferDto;
import com.netcracker.zagursky.exceptions.CatalogException;
import com.netcracker.zagursky.service.OfferService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/offers")
public class OfferController {

    private final static Logger LOGGER = LogManager.getLogger("logger");

    @Autowired
    private OfferService offerService;


    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity createOffer(@RequestBody OfferDto offerDto) throws CatalogException {
        Offer offer=OfferDto.getOfferEntity(offerDto);
        offerService.persist(offer);
        return new ResponseEntity(OfferDto.fromModel(offer), HttpStatus.CREATED);

    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ResponseEntity updateOffer(@RequestBody OfferDto offerDto) throws CatalogException {
        Offer offer=OfferDto.getOfferEntity(offerDto);
        offerService.update(offer);
        return new ResponseEntity(offer,HttpStatus.OK);

    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity getOffers() throws CatalogException {
        List<Offer> offers = offerService.findAll();
        if (offers != null) {
            return new ResponseEntity(OfferDto.fromModel(offers), HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);

        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity getOfferById(@PathVariable Integer id) throws CatalogException {

        Offer offer = offerService.findById(id);
        if (offer != null) {
            return new ResponseEntity(OfferDto.fromModel(offer), HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);

        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteOffer(@PathVariable Integer id) throws CatalogException {

        Offer offer = offerService.findById(id);
        if (offer != null) {
            offerService.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);

        }
    }

    @RequestMapping(value = "/Status/{id}", method = RequestMethod.PUT)
    public ResponseEntity changeOffersStatus(@PathVariable Integer id) throws CatalogException {
        Offer offer = offerService.findById(id);
        if (offer != null) {
            offerService.changeStatus(offer);
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

    }


    @RequestMapping(value = "/available", method = RequestMethod.GET)
    public ResponseEntity getOffersByStatus() throws CatalogException {

        List<Offer> offers = offerService.getAvailableOffers();
        if (offers.size()!=0) {
            return new ResponseEntity(OfferDto.fromModel(offers), HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);

        }
    }

    @RequestMapping(value = "/category/{id}", method = RequestMethod.DELETE)
    public ResponseEntity removeCategory(@PathVariable Integer id) throws CatalogException {
        Offer offer = offerService.removeCategory(id);

        return new ResponseEntity(OfferDto.fromModel(offer), HttpStatus.OK);

    }

    @RequestMapping(value = "/tag/{id}", method = RequestMethod.PUT)
    public ResponseEntity addTag(@PathVariable Integer id, @RequestBody Tag tag) throws CatalogException {
        Offer offer = offerService.addTag(id, tag);
        return new ResponseEntity(OfferDto.fromModel(offer), HttpStatus.OK);

    }

    @RequestMapping(value = "/tag/{id}", method = RequestMethod.DELETE)
    public ResponseEntity removeTag(@PathVariable Integer id, @RequestBody Tag tag) throws CatalogException {
        Offer offer = offerService.removeTag(id, tag);
        return new ResponseEntity(OfferDto.fromModel(offer), HttpStatus.OK);

    }

    @RequestMapping(value = "/category/{id}", method = RequestMethod.PUT)
    public ResponseEntity addCategory(@PathVariable Integer id, @RequestBody Category category) throws CatalogException {
        Offer offer = offerService.setCategory(id, category);

        return new ResponseEntity(OfferDto.fromModel(offer), HttpStatus.OK);

    }

    @RequestMapping(value = "/price/{id}", method = RequestMethod.PUT)
    public ResponseEntity addPrice(@PathVariable Integer id, @RequestBody Price price) throws CatalogException {
        Offer offer = offerService.setPrice(id, price);
        return new ResponseEntity(OfferDto.fromModel(offer), HttpStatus.OK);

    }

    @RequestMapping(value = "/price/{id}/{price}", method = RequestMethod.PUT)
    public ResponseEntity removePrice(@PathVariable Integer id, Integer price) throws CatalogException {
        Offer offer = offerService.changePrice(id, price);
        return new ResponseEntity(OfferDto.fromModel(offer), HttpStatus.OK);

    }

    @RequestMapping(value = "/tags", method = RequestMethod.GET)
    public ResponseEntity getOffersByTags(@RequestParam List<String> tags) throws CatalogException {

        List<Offer> offers = offerService.findByTags(tags);
        if (offers.size()!=0) {
            return new ResponseEntity(OfferDto.fromModel(offers), HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);

        }
    }

    @RequestMapping(value = "/price/{belowPrice}/{uponPrice}", method = RequestMethod.GET)
    public ResponseEntity getOffersByPrice(@PathVariable double belowPrice, @PathVariable double uponPrice) throws CatalogException {

        List<Offer> offers = offerService.findByPrice(belowPrice, uponPrice);
        if (offers.size()!=0) {
            return new ResponseEntity(OfferDto.fromModel(offers), HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);

        }
    }

    @RequestMapping(value = "/category/{categoryName}", method = RequestMethod.GET)
    public ResponseEntity getOffersByCategory(@PathVariable String categoryName) throws CatalogException {

        List<Offer> offers = offerService.findByCategory(categoryName);
        if (offers.size() != 0) {
            return new ResponseEntity(OfferDto.fromModel(offers), HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);

        }
    }

    @RequestMapping(value = "/filter",method = RequestMethod.GET)
    public ResponseEntity getOffersByFilter(@ModelAttribute OffersFilter offersFilter) throws CatalogException {
        List<Offer> offers=offerService.findByFilter(offersFilter);
        if (offers.size() != 0) {
            return new ResponseEntity(OfferDto.fromModel(offers), HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);

        }

    }

    @ExceptionHandler(CatalogException.class)
    public ResponseEntity handleDbException(CatalogException e) {
        LOGGER.error(e);
        return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
    }


}
