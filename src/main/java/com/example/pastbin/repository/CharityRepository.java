package com.example.pastbin.repository;

import com.example.pastbin.dto.Charity.CharityDefaultResponse;
import com.example.pastbin.entity.Charity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharityRepository extends JpaRepository<Charity, Long> {
    boolean existsByBabyPhoto(String babyPhoto);
    @Query("SELECT NEW com.example.pastbin.dto.Charity.CharityDefaultResponse(" +
            "c.babyPhoto, c.originatorName, c.originatorPhoto, c.country, c.story, " +
            "c.targetAmount, c.additionalInfo) FROM Charity c")
    List<CharityDefaultResponse> getAllCharities();
}