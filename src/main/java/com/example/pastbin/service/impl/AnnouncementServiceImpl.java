package com.example.pastbin.service.impl;

import com.example.pastbin.dto.AnnouncementDto.AddAnnouncementRequest;
import com.example.pastbin.entity.Announcement;
import com.example.pastbin.entity.UserInfo;
import com.example.pastbin.entity.enums.Category;
import com.example.pastbin.exceptions.NotFoundException;
import com.example.pastbin.repository.AnnouncementRepository;
import com.example.pastbin.repository.UserInfoRepository;
import com.example.pastbin.service.AnnouncementService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AnnouncementServiceImpl implements AnnouncementService {
    private final AnnouncementRepository announcementRepository;
    private final UserInfoRepository userInfoRepository;
    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public void addAnnouncement(AddAnnouncementRequest request) {
        String gmail = SecurityContextHolder.getContext().getAuthentication().getName();
        UserInfo userInfo = userInfoRepository.getUserAccountByEmail(gmail).orElseThrow(() ->
                new NotFoundException("UserInfo not found"));

        Announcement announcement = new Announcement();
        announcement.setTitle(request.getTitle());
        announcement.setCategory(Category.valueOf(request.getCategory().toLowerCase()));
        announcement.setSubCategory(request.getSubCategory());
        announcement.setCity(request.getCity());
        announcement.setAddress(request.getAddress());
        announcement.setDescription(request.getDescription());
        announcement.setPrice(request.getPrice());
        announcement.setUserName(request.getUserName());
        announcement.setImages(request.getImages());
        announcement.setUser(userInfo.getUser());

        if (userInfo.getUser().getPhoneNumber().isEmpty()) {
            announcement.setPhoneNumber(request.getPhoneNumber());
        } else {
            announcement.setPhoneNumber(userInfo.getUser().getPhoneNumber());
        }

        announcementRepository.save(announcement);
    }

    @Override
    public Map<String, Object> getAllCategories() {
        String cacheKey = "all-categories";
        Map<String, Object> response = (Map<String, Object>) redisTemplate.opsForValue().get(cacheKey);

        if (response == null) {
            response = new HashMap<>();
            for (Category category : Category.values()) {
                response.put(category.name(), category.getFields());
            }
            redisTemplate.opsForValue().set(cacheKey, response, Duration.ofHours(1));
        }

        return response;
    }
}