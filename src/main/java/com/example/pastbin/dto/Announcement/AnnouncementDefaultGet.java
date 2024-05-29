package com.example.pastbin.dto.Announcement;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AnnouncementDefaultGet {
    private String title;
    private String description;
    private Long price;

    public AnnouncementDefaultGet(String title, String description, Long price) {
        this.title = title;
        this.description = description;
        this.price = price;
    }
}