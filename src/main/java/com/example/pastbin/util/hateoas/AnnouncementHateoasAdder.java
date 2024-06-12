package com.example.pastbin.util.hateoas;

import com.example.pastbin.dto.Announcement.GetAnnouncementByIdResponse;
import org.springframework.stereotype.Service;

@Service
public interface AnnouncementHateoasAdder {
    void addLinksToEntity(GetAnnouncementByIdResponse response);
}