package com.netcracker.zagursky.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.netcracker.zagursky.entity.Category;
import com.netcracker.zagursky.entity.Offer;
import com.netcracker.zagursky.entity.Tag;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dzenyaa on 07.12.2017.
 */
public class OfferDto {
    @JsonProperty("id")
    private int id;
    @JsonProperty("description")
    private String description;
    @JsonProperty("name")
    private String name;
    @JsonProperty("status")
    private boolean status;
    @JsonProperty("tags")
    private List<TagDto> tags;
    @JsonProperty("category")
    private CategoryDto category;
    @JsonProperty("price")
    private PriceDto price;


    public static OfferDto fromModel(Offer offer) {
        OfferDto dto = new OfferDto();
        dto.setId(offer.getId());
        dto.setName(offer.getName());
        dto.setDescription(offer.getDescription());
        dto.setStatus(offer.getStatus());
        List<TagDto> tagDtos = new ArrayList<>();
        for (Tag tag : offer.getTags()) {
            tagDtos.add(TagDto.fromModel(tag));
        }
        dto.setTags(tagDtos);
        if (offer.getPrice() != null) {
            dto.setPrice(PriceDto.fromModel(offer.getPrice()));
        }
        if (offer.getCategory() != null) {
            dto.setCategory(CategoryDto.fromModel(offer.getCategory()));
        }
        return dto;
    }

    public static List<OfferDto> fromModel(List<Offer> offers) {
        List<OfferDto> dto = new ArrayList<>();
        for (Offer offer : offers) {
            dto.add(OfferDto.fromModel(offer));
        }
        return dto;
    }

    public static Offer getOfferEntity(OfferDto offerDto) {
        Offer offer = new Offer();
        offer.setId(offerDto.getId());
        offer.setName(offerDto.getName());
        offer.setDescription(offerDto.getDescription());
        offer.setStatus(offerDto.getStatus());
        if (offerDto.getPrice()!=null)
        {
            offer.setPrice(PriceDto.getPriceEntity(offerDto.getPrice()));
        }
        if (offerDto.getCategory()!=null)
        {
            offer.setCategory(CategoryDto.getCategoryEntity(offerDto.getCategory()));
        }
        if (offer.getTags().size()!=0)
        {
            offer.setTags(TagDto.getTagEntity(offerDto.getTags()));
        }
        return offer;
    }

    public static List<Offer> getOfferEntity(List<OfferDto> offerDtoList) {
        List<Offer> offers = new ArrayList<>();
        for (OfferDto offerDto : offerDtoList) {
            offers.add(OfferDto.getOfferEntity(offerDto));
        }
        return offers;
    }

    public List<TagDto> getTags() {
        return tags;
    }

    public void setTags(List<TagDto> tags) {
        this.tags = tags;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CategoryDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDto category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PriceDto getPrice() {
        return price;
    }

    public void setPrice(PriceDto price) {
        this.price = price;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
