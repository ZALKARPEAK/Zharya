package com.example.pastbin.service.impl;

import com.example.pastbin.dto.Charity.CharityDefaultResponse;
import com.example.pastbin.dto.Charity.CharityRequestCreate;
import com.example.pastbin.entity.Charity;
import com.example.pastbin.entity.Donation;
import com.example.pastbin.entity.UserInfo;
import com.example.pastbin.exceptions.NotFoundException;
import com.example.pastbin.repository.CharityRepository;
import com.example.pastbin.repository.DonationRepository;
import com.example.pastbin.repository.UserInfoRepository;
import com.example.pastbin.service.CharityService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CharityServiceImpl implements CharityService {
    private final CharityRepository charityRepository;
    private final UserInfoRepository userInfoRepository;
    private final DonationRepository donationRepository;

    @Override
    public void createCharity(CharityRequestCreate charityRequestCreate) throws BadRequestException {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        UserInfo userInfo = userInfoRepository.getUserAccountByEmail(email).orElseThrow(() ->
                new NotFoundException("UserInfo not found"));

        if (charityRepository.existsByBabyPhoto(charityRequestCreate.getBabyPhoto())) {
            throw new BadRequestException("Charity with the same baby photo already exists");
        }

        Charity charity = new Charity();
        charity.setBabyPhoto(charityRequestCreate.getBabyPhoto());
        charity.setOriginatorName(charityRequestCreate.getOriginatorName());
        charity.setOriginatorPhoto(charityRequestCreate.getOriginatorPhoto());
        charity.setCountry(charityRequestCreate.getCountry());
        charity.setStory(charityRequestCreate.getStory());
        charity.setTargetAmount(charityRequestCreate.getTargetAmount());
        charity.setAdditionalInfo(charityRequestCreate.getAdditionalInfo());
        charity.setDonationAccount(userInfo.getUser());

        charityRepository.save(charity);
    }

    @Override
    public void deleteCharity(Long charityId) {
        Charity charity = charityRepository.findById(charityId).orElseThrow(() ->
                new NotFoundException("Charity not found"));

        charityRepository.delete(charity);
    }

    @Override
    public void donateToCharity(Long charityId, double donationAmount) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        UserInfo userInfo = userInfoRepository.getUserAccountByEmail(email).orElseThrow(() ->
                new NotFoundException("UserInfo not found"));

        Charity charity = charityRepository.findById(charityId).orElseThrow(() ->
                new NotFoundException("Charity not found"));

        Donation donation = Donation.builder()
                .amount(donationAmount)
                .donorName(userInfo.getUsername())
                .donorEmail(userInfo.getEmail())
                .donationDate(LocalDateTime.now())
                .charity(charity)
                .build();

        double amountCharity = charity.getTargetAmount();
        charity.setTargetAmount(amountCharity - donationAmount);

        charityRepository.save(charity);
        donationRepository.save(donation);
    }

    @Override
    public List<CharityDefaultResponse> charityResponse() {
        return charityRepository.getAllCharities();
    }
}