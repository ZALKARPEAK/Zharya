package com.example.pastbin.service.impl;

import com.example.pastbin.dto.Announcement.AddAnnouncementRequest;
import com.example.pastbin.dto.Announcement.GetAnnouncementByIdResponse;
import com.example.pastbin.dto.Announcement.UpdateAnnouncementRequest;
import com.example.pastbin.entity.Announcement;
import com.example.pastbin.entity.UserInfo;
import com.example.pastbin.entity.enums.Category;
import com.example.pastbin.exceptions.NotFoundException;
import com.example.pastbin.repository.AnnouncementRepository;
import com.example.pastbin.repository.UserInfoRepository;
import com.example.pastbin.service.AnnouncementService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
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
        announcementFields(announcement, request);
        announcement.setUser(userInfo.getUser());

        if (userInfo.getUser().getPhoneNumber().isEmpty()) {
            announcement.setPhoneNumber(request.getPhoneNumber());
        } else {
            announcement.setPhoneNumber(userInfo.getUser().getPhoneNumber());
        }

        announcementRepository.save(announcement);
    }

    @Override
    public void editAnnouncement(Long announcementId, UpdateAnnouncementRequest request) {
        Announcement announcement = announcementRepository.findById(announcementId).orElseThrow(
                () -> new NotFoundException("Announcement not found"));

        String gmail = SecurityContextHolder.getContext().getAuthentication().getName();
        UserInfo userInfo = userInfoRepository.getUserAccountByEmail(gmail)
                .orElseThrow(() -> new NotFoundException("UserInfo not found"));

        if (announcement.getUser().equals(userInfo.getUser())) {
            announcementFields(announcement, request);
            List<String> images = new java.util.ArrayList<>(announcement.getImages().stream().
                    filter(image -> request.getImagesToDelete().contains(image)).
                    toList());
            announcement.setImages(images);

            List<String> newImages = request.getImages();
            if (newImages != null) {
                images.addAll(newImages);
                announcement.setImages(images);
            }

            announcementRepository.save(announcement);
        }
    }

    private void announcementFields(Announcement announcement, Object request) {
        if (request instanceof AddAnnouncementRequest addRequest) {
            announcement.setTitle(addRequest.getTitle());
            announcement.setCategory(Category.fromDisplayName(addRequest.getCategory()));
            announcement.setSubCategory(addRequest.getSubCategory());
            announcement.setCity(addRequest.getCity());
            announcement.setAddress(addRequest.getAddress());
            announcement.setDescription(addRequest.getDescription());
            announcement.setPrice(addRequest.getPrice());
            announcement.setUserName(addRequest.getUserName());
            announcement.setImages(addRequest.getImages());
        } else if (request instanceof UpdateAnnouncementRequest updateRequest) {
            announcement.setTitle(updateRequest.getTitle());
            announcement.setCategory(Category.fromDisplayName(updateRequest.getCategory())  );
            announcement.setSubCategory(updateRequest.getSubCategory());
            announcement.setCity(updateRequest.getCity());
            announcement.setAddress(updateRequest.getAddress());
            announcement.setDescription(updateRequest.getDescription());
            announcement.setPrice(updateRequest.getPrice());
            announcement.setUserName(updateRequest.getUserName());
            announcement.setImages(updateRequest.getImages());
        }
    }

    @Override
    public void removeAnnouncement(Long announcementId) {
        String gmail = SecurityContextHolder.getContext().getAuthentication().getName();
        UserInfo userInfo = userInfoRepository.getUserAccountByEmail(gmail)
                .orElseThrow(() -> new NotFoundException("UserInfo not found"));

        Announcement announcement = announcementRepository.findById(announcementId)
                .orElseThrow(() -> new NotFoundException("Announcement not found"));

        if (!announcement.getUser().equals(userInfo.getUser())) {
            throw new AccessDeniedException("You are not the owner of this announcement");
        }

        announcementRepository.delete(announcement);
    }

    @Override
    public Map<String, Object> getAllCategories() {
        String cacheKey = "all-categories";
        Map<String, Object> response = (Map<String, Object>) redisTemplate.opsForValue().get(cacheKey);

        if (response == null) {
            response = new HashMap<>();
            for (Category category : Category.values()) {
                response.put(category.name(), category.getSubCategories());
            }
            redisTemplate.opsForValue().set(cacheKey, response, Duration.ofDays(7));
        }

        return response;
    }

    @Override
    public GetAnnouncementByIdResponse getAnnouncementById(Long announcementId) {
        Announcement announcement = announcementRepository.findById(announcementId).orElseThrow(() ->
                new NotFoundException("Announcement not found"));

        return GetAnnouncementByIdResponse.builder()
                .title(announcement.getTitle())
                .category(announcement.getCategory())
                .subCategory(announcement.getSubCategory())
                .city(announcement.getCity())
                .address(announcement.getAddress())
                .description(announcement.getDescription())
                .price(announcement.getPrice())
                .images(announcement.getImages())
                .userName(announcement.getUserName())
                .phoneNumber(announcement.getPhoneNumber())
                .build();
    }
}