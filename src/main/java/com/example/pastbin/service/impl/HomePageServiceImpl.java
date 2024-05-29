package com.example.pastbin.service.impl;

import com.example.pastbin.dto.Announcement.AnnouncementDefaultGet;
import com.example.pastbin.entity.enums.Category;
import com.example.pastbin.repository.AnnouncementRepository;
import com.example.pastbin.service.HomePageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HomePageServiceImpl implements HomePageService {
    private final AnnouncementRepository announcementRepository;

    @Override
    public List<AnnouncementDefaultGet> defaultGetAllAnnouncement() {
        return announcementRepository.getAllAnnouncementHomePage();
    }

    @Override
    public List<AnnouncementDefaultGet> getAnnouncementSearch(String word) {
        return announcementRepository.getAnnouncementByTitleContainsIgnoreCase(word);
    }

    @Override
    public List<AnnouncementDefaultGet> getFilteredAnnouncements(List<String> categories, List<String> subCategories) {
        List<Category> categoryEnums = categories != null ? categories.stream()
                .map(Category::fromDisplayName)
                .collect(Collectors.toList()) : Collections.emptyList();

        List<AnnouncementDefaultGet> results;
        if (!categoryEnums.isEmpty() && subCategories != null && !subCategories.isEmpty()) {
            results = announcementRepository.findByCategoriesAndSubCategories(categoryEnums, subCategories);
        } else if (!categoryEnums.isEmpty()) {
            results = announcementRepository.findByCategories(categoryEnums);
        } else if (subCategories != null && !subCategories.isEmpty()) {
            results = announcementRepository.findBySubCategories(subCategories);
        } else {
            results = announcementRepository.getAllAnnouncementHomePage();
        }

        return results;
    }
}