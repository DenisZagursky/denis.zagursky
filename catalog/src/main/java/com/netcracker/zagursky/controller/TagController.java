package com.netcracker.zagursky.controller;

import com.netcracker.zagursky.entity.dto.OfferDto;
import com.netcracker.zagursky.entity.dto.TagDto;
import com.netcracker.zagursky.entity.Offer;
import com.netcracker.zagursky.entity.Tag;
import com.netcracker.zagursky.exceptions.CatalogException;
import com.netcracker.zagursky.service.TagService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Dzenyaa on 04.12.2017.
 */

@RestController
@RequestMapping("api/v1/tags")
public class TagController {

    private final static Logger LOGGER = LogManager.getLogger("logger");


    @Autowired
    private TagService tagService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity createTag(@RequestBody TagDto tagDto) throws CatalogException {
        Tag tag=TagDto.getTagEntity(tagDto);
        tagService.persist(tag);
        return new ResponseEntity(TagDto.fromModel(tag), HttpStatus.CREATED);

    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ResponseEntity updateTag(@RequestBody TagDto tagDto) throws CatalogException {
        Tag tag=TagDto.getTagEntity(tagDto);
        tagService.update(tag);
        return new ResponseEntity(HttpStatus.OK);

    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity getTags() throws CatalogException {
        List<Tag> tags = tagService.findAll();
        if (tags.size()!=0) {
            return new ResponseEntity(TagDto.fromModel(tags), HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);

        }
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity getTagById(@PathVariable Integer id) throws CatalogException {

        Tag tag = tagService.findById(id);
        if (tag != null) {
            return new ResponseEntity(TagDto.fromModel(tag), HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);

        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteTag(@PathVariable Integer id) throws CatalogException {

        Tag tag = tagService.findById(id);
        if (tag != null) {
            tagService.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);

        }
    }

    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    public ResponseEntity getTagByName(@PathVariable String name) throws CatalogException {
        Tag tag = tagService.findByName(name);
        if (tag != null) {
            return new ResponseEntity(TagDto.fromModel(tag), HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);

        }
    }

    @RequestMapping(value = "/offers/{id}", method = RequestMethod.GET)
    public ResponseEntity getCategoryOffers(@PathVariable Integer id) throws CatalogException {
        List<Offer> offers = tagService.getOffers(id);
        if (offers.size()!=0) {
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
