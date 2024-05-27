package com.example.pastbin.dto.Announcement;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UpdateAnnouncementRequest {
    private String title;
    private String category;
    private String subCategory;
    private String city;
    private String address;
    @Size(min = 80)
    private String description;
    private Long price;
    private String userName;
    private List<String> images;
    private List<Long> imagesToDelete;
}