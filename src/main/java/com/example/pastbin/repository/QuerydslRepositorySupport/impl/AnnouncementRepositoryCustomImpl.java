package com.example.pastbin.repository.QuerydslRepositorySupport.impl;

import com.example.pastbin.dto.Announcement.AnnouncementDefaultGet;
import com.example.pastbin.repository.QuerydslRepositorySupport.AnnouncementRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AnnouncementRepositoryCustomImpl implements AnnouncementRepositoryCustom {

    @Override
    public List<AnnouncementDefaultGet> getAllAnnouncementDefault() {
        return null;
    }
}
