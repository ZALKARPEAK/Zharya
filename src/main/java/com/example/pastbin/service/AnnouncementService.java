package com.example.pastbin.service;

import com.example.pastbin.dto.Announcement.AddAnnouncementRequest;
import com.example.pastbin.dto.Announcement.GetAnnouncementByIdResponse;
import com.example.pastbin.dto.Announcement.UpdateAnnouncementRequest;

import java.util.Map;

public interface AnnouncementService {
    void addAnnouncement(AddAnnouncementRequest request);
    void editAnnouncement(Long announcementId, UpdateAnnouncementRequest request);
    void removeAnnouncement(Long announcementId);
    Map<String, Object> getAllCategories();
    GetAnnouncementByIdResponse getAnnouncementById(Long announcementId);
}