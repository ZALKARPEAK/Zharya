package com.example.pastbin.util.dto;

import com.example.pastbin.util.additional.Mappable;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

@Data
@Getter
@Builder
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CharityDto extends RepresentationModel<CharityDto> implements Mappable {
    private Long id;
    private String babyPhoto;
    private String originatorName;
    private String originatorPhoto;
    private String country;
    private String story;
    private double targetAmount;
    private String additionalInfo;
    private Long donationAccountId;
}