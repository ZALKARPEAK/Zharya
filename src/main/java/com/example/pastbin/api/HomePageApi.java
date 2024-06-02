package com.example.pastbin.api;

import com.example.pastbin.dto.Announcement.AnnouncementDefaultGet;
import com.example.pastbin.service.HomePageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/announcement")
@CrossOrigin
@Tag(name = "HomePage api", description = "HomePage endpoints")
public class HomePageApi {
    private final HomePageService homePageService;

    @GetMapping("/get-announcement-default")
    @Operation(
            summary = "Get All Announcements",
            description = "Retrieves a list of all announcements with default response fields, including announcement title, description, image, and category."
    )
    public List<AnnouncementDefaultGet> announcementDefaultGet() {
        return homePageService.defaultGetAllAnnouncement();
    }

    @GetMapping("/get-announcement-search")
    @Operation(
            summary = "Search Announcements",
            description = "Retrieves a list of announcements that match the provided search word, with default response fields."
    )
    public List<AnnouncementDefaultGet> getAnnouncementSearch(@RequestParam String word) {
        return homePageService.getAnnouncementSearch(word);
    }

    @PostMapping("/get-announcement-filter")
    @Operation(
            summary = "Filter Announcements",
            description = "Retrieves a list of announcements filtered by the provided categories and sub-categories, with default response fields."
    )
    public List<AnnouncementDefaultGet> getFilteredAnnouncements(@RequestParam(required = false) List<String> categories,
                                                                 @RequestParam(required = false) List<String> subCategories) {
        return homePageService.getFilteredAnnouncements(categories, subCategories);
    }
}