package com.example.pastbin.repository.QuerydslRepositorySupport;

import com.example.pastbin.dto.AnnouncementDto.AnnouncementDefaultGet;

import java.util.List;

public interface AnnouncementRepositoryCustom {
    List<AnnouncementDefaultGet> getAllAnnouncementDefault();
}