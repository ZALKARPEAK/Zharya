package com.example.pastbin.util.hateoas.impl;

import com.example.pastbin.api.AnnouncementApi;
import com.example.pastbin.dto.Announcement.GetAnnouncementByIdResponse;
import com.example.pastbin.dto.Announcement.UpdateAnnouncementRequest;
import com.example.pastbin.util.hateoas.AnnouncementHateoasAdder;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

@Service
public class AnnouncementHateoas implements AnnouncementHateoasAdder {

    @Override
    public void addLinksToEntity(GetAnnouncementByIdResponse response, Long id) {
        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AnnouncementApi.class)
                .getAnnouncementByIdResponse(id)).withSelfRel());

        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AnnouncementApi.class)
                .editAnnouncement(id, new UpdateAnnouncementRequest())).withRel("edit"));

        response .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AnnouncementApi.class)
                .removeAnnouncement(id)).withRel("delete"));
    }
}
