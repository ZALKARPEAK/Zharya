package com.example.pastbin.dto.Charity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CharityRequestCreate {
    private String babyPhoto;
    private String originatorName;
    private String originatorPhoto;
    private String country;
    private String story;
    private double targetAmount;
    private String additionalInfo;
}