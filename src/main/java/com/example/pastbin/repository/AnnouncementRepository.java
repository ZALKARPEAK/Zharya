package com.example.pastbin.repository;

import com.example.pastbin.dto.Announcement.AnnouncementDefaultGet;
import com.example.pastbin.entity.Announcement;
import com.example.pastbin.entity.enums.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {
    @Query("SELECT new com.example.pastbin.dto.Announcement.AnnouncementDefaultGet(a.title, a.description, a.price) FROM Announcement a")
    List<AnnouncementDefaultGet> getAllAnnouncementHomePage();
    List<AnnouncementDefaultGet> getAnnouncementByTitleContainsIgnoreCase(String word);
    @Query("SELECT new com.example.pastbin.dto.Announcement.AnnouncementDefaultGet(a.title, a.description, a.price) " +
            "FROM Announcement a WHERE a.category IN :categories AND a.subCategory IN :subCategories")
    List<AnnouncementDefaultGet> findByCategoriesAndSubCategories(
            @Param("categories") List<Category> categories,
            @Param("subCategories") List<String> subCategories);

    @Query("SELECT new com.example.pastbin.dto.Announcement.AnnouncementDefaultGet(a.title, a.description, a.price) " +
            "FROM Announcement a WHERE a.category IN :categories")
    List<AnnouncementDefaultGet> findByCategories(@Param("categories") List<Category> categories);

    @Query("SELECT new com.example.pastbin.dto.Announcement.AnnouncementDefaultGet(a.title, a.description, a.price) " +
            "FROM Announcement a WHERE a.subCategory IN :subCategories")
    List<AnnouncementDefaultGet> findBySubCategories(@Param("subCategories") List<String> subCategories);


}