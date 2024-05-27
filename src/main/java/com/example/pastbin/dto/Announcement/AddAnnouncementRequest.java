package com.example.pastbin.dto.Announcement;

import com.example.pastbin.util.validator.ValidPhoneNumber;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AddAnnouncementRequest {
    private String category;

        private String subCategory;

    private String title;

    private String city;

    private String address;

    @Size(min = 80, message = "Description must be at least 80 characters long")
    private String description;

    @Min(0)
    private Long price;

    private List<String> images;

    @ValidPhoneNumber
    private String phoneNumber;

    private String userName;
}