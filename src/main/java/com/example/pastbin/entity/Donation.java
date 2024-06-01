package com.example.pastbin.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode()
public class Donation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "donation_generator")
    @SequenceGenerator(name = "donation_generator", sequenceName = "donation_sequence", allocationSize = 1)
    private Long id;
    private double amount;
    private String donorName;
    private String donorEmail;
    private LocalDateTime donationDate;
    @ManyToOne
    private Charity charity;
}