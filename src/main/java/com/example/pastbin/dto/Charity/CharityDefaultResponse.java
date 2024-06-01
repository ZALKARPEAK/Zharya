package com.example.pastbin.dto.Charity;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
public class CharityDefaultResponse {
    private String babyPhoto;
    private String originatorName;
    private String originatorPhoto;
    private String country;
    private String story;
    private double targetAmount;
    private String additionalInfo;

    public CharityDefaultResponse(String babyPhoto, String originatorName, String originatorPhoto, String country, String story, double targetAmount, String additionalInfo) {
        this.babyPhoto = babyPhoto;
        this.originatorName = originatorName;
        this.originatorPhoto = originatorPhoto;
        this.country = country;
        this.story = story;
        this.targetAmount = targetAmount;
        this.additionalInfo = additionalInfo;
    }
}