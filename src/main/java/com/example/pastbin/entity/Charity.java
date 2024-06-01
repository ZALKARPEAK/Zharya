package com.example.pastbin.entity;

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
public class Charity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "charity_generator")
    @SequenceGenerator(name = "charity_generator", sequenceName = "charity_sequence", allocationSize = 1)
    private Long id;
    private String babyPhoto;
    private String originatorName;
    private String originatorPhoto;
    private String country;
    private String story;
    private double targetAmount;
    private String additionalInfo;
    @ManyToOne
    private User donationAccount;
    @OneToMany(mappedBy = "charity")
    private List<Donation> donationList;
}