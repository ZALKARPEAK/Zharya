package com.example.pastbin.util.hateoas.impl;

import com.example.pastbin.api.AnnouncementApi;
import com.example.pastbin.dto.Announcement.AnnouncementDefaultGet;
import com.example.pastbin.util.hateoas.HomePageHateoasAdder;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import java.util.List;

public class HomePageHateoas implements HomePageHateoasAdder {

    @Override
    public void addLinksToListEntity(List<AnnouncementDefaultGet> response) {
        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AnnouncementApi.class));
    }
}