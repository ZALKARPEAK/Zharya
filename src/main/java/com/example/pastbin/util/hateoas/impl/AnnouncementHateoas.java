package com.example.pastbin.util.hateoas.impl;

import com.example.pastbin.api.AnnouncementApi;
import com.example.pastbin.dto.Announcement.GetAnnouncementByIdResponse;
import com.example.pastbin.util.hateoas.AnnouncementHateoasAdder;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

public class AnnouncementHateoas implements AnnouncementHateoasAdder {

    @Override
    public void addLinksToEntity(GetAnnouncementByIdResponse response) {
    }
}
