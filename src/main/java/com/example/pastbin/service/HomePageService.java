package com.example.pastbin.service;

import com.example.pastbin.dto.Announcement.AnnouncementDefaultGet;

import java.util.List;

public interface HomePageService {
    List<AnnouncementDefaultGet> defaultGetAllAnnouncement();
    List<AnnouncementDefaultGet> getAnnouncementSearch(String word);
    List<AnnouncementDefaultGet> getFilteredAnnouncements(List<String> categories, List<String> subCategories);
}