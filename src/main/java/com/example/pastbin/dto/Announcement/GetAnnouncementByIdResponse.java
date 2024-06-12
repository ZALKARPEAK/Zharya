package com.example.pastbin.dto.Announcement;

import com.example.pastbin.entity.enums.Category;
import com.example.pastbin.util.additional.Mappable;
import lombok.Builder;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@Builder
@Setter
public class GetAnnouncementByIdResponse extends RepresentationModel<GetAnnouncementByIdResponse> implements Mappable {
    private String title;
    private Category category;
    private String subCategory;
    private String city;
    private String address;
    private String description;
    private Long price;
    private List<String> images;
    private String userName;
    private String phoneNumber;
}