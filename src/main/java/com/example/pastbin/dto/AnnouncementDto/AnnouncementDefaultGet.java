package com.example.pastbin.dto.AnnouncementDto;

import lombok.Builder;
import lombok.Setter;

@Setter
@Builder
public class AnnouncementDefaultGet {
    private String name;
    private String description;
    private String price;
}