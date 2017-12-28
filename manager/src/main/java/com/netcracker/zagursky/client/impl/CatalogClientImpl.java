package com.netcracker.zagursky.client.impl;

import com.netcracker.zagursky.client.CatalogClient;
import com.netcracker.zagursky.entity.catalog.Category;
import com.netcracker.zagursky.entity.catalog.Offer;
import com.netcracker.zagursky.entity.catalog.OffersFilter;
import com.netcracker.zagursky.entity.catalog.Tag;
import com.netcracker.zagursky.exception.ManagerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by Dzenyaa on 11.12.2017.
 */
@Component
public class CatalogClientImpl implements CatalogClient {


    @Autowired
    HttpHeaders headers;
    @Value("${endpoint.catalog}")
    private String PATH_TO_CATALOG;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<Offer> getOffersByTags(String tags) throws ManagerException {
        try {
            HttpEntity request = new HttpEntity(headers);
            ResponseEntity<List<Offer>> orderResponseEntity = restTemplate.exchange(PATH_TO_CATALOG + "offers/tags?tags=" + tags,
                    HttpMethod.GET,
                    request,
                    new ParameterizedTypeReference<List<Offer>>() {
                    });
            return orderResponseEntity.getBody();
        } catch (Exception e) {
            throw new ManagerException("wrong get offers from tags", e);
        }
    }

    @Override
    public List<Offer> getOffersByCategory(String categoryName) throws ManagerException {
        try {
            HttpEntity request = new HttpEntity(headers);
            ResponseEntity<List<Offer>> orderResponseEntity = restTemplate.exchange(PATH_TO_CATALOG + "offers/category/" + categoryName,
                    HttpMethod.GET,
                    request,
                    new ParameterizedTypeReference<List<Offer>>() {
                    });
            return orderResponseEntity.getBody();
        } catch (Exception e) {
            throw new ManagerException("wrong get offers from category", e);
        }
    }

    @Override
    public List<Offer> getOffersInPriceRange(Double belowPrice, double uponPrice) throws ManagerException {
        try {
            HttpEntity request = new HttpEntity(headers);
            ResponseEntity<List<Offer>> orderResponseEntity = restTemplate.exchange(PATH_TO_CATALOG + "offers/price/" + belowPrice + "/" + uponPrice,
                    HttpMethod.GET,
                    request,
                    new ParameterizedTypeReference<List<Offer>>() {
                    });
            return orderResponseEntity.getBody();
        } catch (Exception e) {
            throw new ManagerException("wrong get offers from price", e);
        }
    }

    @Override
    public List<Category> getCategories() throws ManagerException {
        try {
            HttpEntity request = new HttpEntity(headers);
            ResponseEntity<List<Category>> orderResponseEntity = restTemplate.exchange(PATH_TO_CATALOG + "/categories/",
                    HttpMethod.GET,
                    request,
                    new ParameterizedTypeReference<List<Category>>() {
                    });
            return orderResponseEntity.getBody();
        } catch (Exception e) {
            throw new ManagerException("wrong get  categories", e);
        }
    }

    @Override
    public List<Tag> getTags() throws ManagerException {
        try {
            HttpEntity request = new HttpEntity(headers);
            ResponseEntity<List<Tag>> orderResponseEntity = restTemplate.exchange(PATH_TO_CATALOG + "/tags/",
                    HttpMethod.GET,
                    request,
                    new ParameterizedTypeReference<List<Tag>>() {
                    });
            return orderResponseEntity.getBody();
        } catch (Exception e) {
            throw new ManagerException("wrong get tags", e);
        }
    }

    @Override
    public Offer getOfferById(Integer id) throws ManagerException {
        try {
            HttpEntity request = new HttpEntity(headers);
            ResponseEntity<Offer> orderResponseEntity = restTemplate.exchange(PATH_TO_CATALOG + "/offers/"+id,
                    HttpMethod.GET,
                    request, Offer.class
                    );
            return orderResponseEntity.getBody();
        } catch (Exception e) {
            throw new ManagerException("wrong get  category By Id", e);
        }
    }

    @Override
    public List<Offer> getOffersByFilter(OffersFilter filter) throws ManagerException {
        try {
            HttpEntity request = new HttpEntity(filter,headers);
            ResponseEntity<List<Offer>> orderResponseEntity = restTemplate.exchange(PATH_TO_CATALOG + "offers/filter/",
                    HttpMethod.GET,
                    request,
                    new ParameterizedTypeReference<List<Offer>>() {
                    });
            return orderResponseEntity.getBody();
        } catch (Exception e) {
            throw new ManagerException("wrong get offers by filter", e);
        }
    }
}
