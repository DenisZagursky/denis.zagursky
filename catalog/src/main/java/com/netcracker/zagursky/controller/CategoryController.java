package com.netcracker.zagursky.controller;

import com.netcracker.zagursky.dto.CategoryDto;
import com.netcracker.zagursky.dto.OfferDto;
import com.netcracker.zagursky.entity.Category;
import com.netcracker.zagursky.entity.Offer;
import com.netcracker.zagursky.exceptions.DbException;
import com.netcracker.zagursky.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Dzenyaa on 03.12.2017.
 */
@RestController
@RequestMapping("api/v1/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity createCategory(@RequestBody Category category) throws DbException {

        categoryService.persist(category);
        return new ResponseEntity(CategoryDto.fromModel(category), HttpStatus.CREATED);

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity updateCategory(@RequestBody Category category) throws DbException {
        categoryService.update(category);
        return new ResponseEntity(CategoryDto.fromModel(category), HttpStatus.OK);

    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity getCategories() throws DbException {
        List<Category> categories = categoryService.findAll();
        if (categories != null) {
            return new ResponseEntity(CategoryDto.fromModel(categories), HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);

        }
    }


    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity getCategoryById(@PathVariable Integer id) throws DbException {

        Category category = categoryService.findById(id);
        if (category != null) {
            return new ResponseEntity(CategoryDto.fromModel(category), HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);

        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteCategory(@PathVariable Integer id) throws DbException {

        Category category = categoryService.findById(id);
        if (category != null) {
            categoryService.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);

        }
    }

    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    public ResponseEntity getCategoryByName(@PathVariable String name) throws DbException {
        Category category = categoryService.findByName(name);
        if (category != null) {
            return new ResponseEntity(CategoryDto.fromModel(category), HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);

        }
    }

    @RequestMapping(value = "/offers/{id}", method = RequestMethod.GET)
    public ResponseEntity getCategoryOffers(@PathVariable Integer id) throws DbException {
        List<Offer> offer = categoryService.getOffers(id);
        if (offer != null) {
            return new ResponseEntity(OfferDto.fromModel(offer), HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);

        }
    }

    @ExceptionHandler(DbException.class)
    public ResponseEntity handleDbException(DbException e) {
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
