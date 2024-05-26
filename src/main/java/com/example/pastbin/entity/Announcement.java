package com.example.pastbin.entity;

import com.example.pastbin.entity.enums.Category;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode()
public class Announcement implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "announcement_generator")
    @SequenceGenerator(name = "announcement_generator", sequenceName = "announcement_sequence", allocationSize = 1)
    private Long id;
    private String title;
    @Enumerated(EnumType.STRING)
    private Category category;
    private String subCategory;
    private String city;
    private String address;
    private String description;
    private Long price;
    @ElementCollection
    @Column(name = "images", length = 25000)
    private List<String> images;
    private String userName;
    private String phoneNumber;

    @ManyToOne
    private User user;

    public void setSubCategory(String subCategory) {
        if (category != null && category.getFields().contains(subCategory)) {
            this.subCategory = subCategory;
        } else {
            throw new IllegalArgumentException("Invalid subcategory for the selected category");
        }
    }
}
