package com.example.pastbin.repository.QuerydslRepositorySupport;

import com.example.pastbin.dto.Announcement.AnnouncementDefaultGet;

import java.util.List;

public interface AnnouncementRepositoryCustom {
    List<AnnouncementDefaultGet> getAllAnnouncementDefault();
}