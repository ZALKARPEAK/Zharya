package com.example.pastbin.util.hateoas;

import com.example.pastbin.dto.Announcement.GetAnnouncementByIdResponse;

public interface AnnouncementHateoasAdder {
    void addLinksToEntity(GetAnnouncementByIdResponse response, Long id);
}