package com.example.pastbin.service;

import com.example.pastbin.dto.AnnouncementDto.AddAnnouncementRequest;

import java.util.Map;

public interface AnnouncementService {
    void addAnnouncement(AddAnnouncementRequest request);
    Map<String, Object> getAllCategories();
}