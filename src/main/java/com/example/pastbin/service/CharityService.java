package com.example.pastbin.service;

import com.example.pastbin.dto.Charity.CharityDefaultResponse;
import com.example.pastbin.dto.Charity.CharityRequestCreate;
import org.apache.coyote.BadRequestException;

import java.util.List;

public interface CharityService {
    void createCharity(CharityRequestCreate charityRequestCreate) throws BadRequestException;
    void deleteCharity(Long charityId);
    void donateToCharity(Long charityId, double donationAmount);
    List<CharityDefaultResponse> charityResponse();
}