package com.netcracker.zagursky.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.netcracker.zagursky.entity.Tag;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dzenyaa on 07.12.2017.
 */
public class TagDto {
    @JsonProperty("id")
    private int id;
    @JsonProperty("name")
    private String name;

    public static TagDto fromModel(Tag tag) {
        TagDto dto = new TagDto();
        dto.setId(tag.getId());
        dto.setName(tag.getName());
        return dto;
    }

    public static Tag getTagEntity(TagDto tagDto) {
        Tag tag = new Tag();
        tag.setId(tagDto.getId());
        tag.setName(tagDto.getName());
        return tag;
    }

    public static List<TagDto> fromModel(List<Tag> tags) {
        List<TagDto> dto = new ArrayList<>();
        for (Tag tag : tags) {
            dto.add(TagDto.fromModel(tag));
        }
        return dto;
    }

    public static List<Tag> getTagEntity(List<TagDto> tagDtoList) {
        List<Tag> tags = new ArrayList<>();
        for (TagDto tagDto : tagDtoList) {
            tags.add(TagDto.getTagEntity(tagDto));
        }
        return tags;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
