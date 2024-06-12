package com.example.pastbin.util.hateoas;

import com.example.pastbin.dto.Announcement.AnnouncementDefaultGet;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HomePageHateoasAdder {
    void addLinksToListEntity(List<AnnouncementDefaultGet> response);
}