package com.example.pastbin.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity(name = "Users")
@Builder
@Data
@EqualsAndHashCode()
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_generator")
    @SequenceGenerator(name = "user_generator", sequenceName = "user_sequence_name", allocationSize = 1,
    initialValue = 11)
    private Long id;
    private String name;
    private String phoneNumber;
    private String image;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private UserInfo userInfo;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.DETACH, CascadeType.REMOVE})
    @Transient
    private List<Announcement> announcements;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}