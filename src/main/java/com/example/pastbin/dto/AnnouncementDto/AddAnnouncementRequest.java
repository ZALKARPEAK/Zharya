package com.example.pastbin.dto.AnnouncementDto;

import com.example.pastbin.util.validator.ValidPhoneNumber;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class AddAnnouncementRequest {
    @NotBlank
    private String category;

    @NotBlank
    private String subCategory;

    @NotBlank
    private String title;

    @NotBlank
    private String city;

    @NotBlank
    private String address;

    @NotBlank
    @Size(min = 80, message = "Description must be at least 80 characters long")
    private String description;

    @NotBlank
    private Long price;

    private List<String> images;

    @ValidPhoneNumber
    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String userName;
}