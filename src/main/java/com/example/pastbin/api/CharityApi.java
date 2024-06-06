package com.example.pastbin.api;

import com.example.pastbin.api.response.CustomResponse;
import com.example.pastbin.dto.Charity.CharityDefaultResponse;
import com.example.pastbin.dto.Charity.CharityRequestCreate;
import com.example.pastbin.service.CharityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/charity")
@CrossOrigin
@Tag(name = "Charity api", description = "Charity endpoints")
public class CharityApi {
    private final CharityService charityService;

    @PostMapping("/create-charity")
    @Operation(
            summary = "Create Charity",
            description = "Creates a new charity in the system."
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    public CustomResponse<?> createCharity(@RequestBody CharityRequestCreate charityRequestCreate) throws BadRequestException {
        charityService.createCharity(charityRequestCreate);
        return new CustomResponse<>(HttpStatus.OK, "Charity created successfully");
    }

    @DeleteMapping("/delete-charity")
    @Operation(
            summary = "Delete Charity",
            description = "Deletes an existing charity from the system."
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    public CustomResponse<?> deleteCharity(@RequestParam Long charityId) {
        charityService.deleteCharity(charityId);
        return new CustomResponse<>(HttpStatus.OK, "Charity deleted successfully");
    }

    @PostMapping("/donate-to-charity")
    @Operation(
            summary = "Donate to a Charity",
            description = "Allows a user to make a donation to a specified charity.")
    @PermitAll
    public CustomResponse<?> donateToCharity(@RequestParam Long charityId, double donationAmount) {
        charityService.donateToCharity(charityId, donationAmount);
        return new CustomResponse<>(HttpStatus.OK, "Charity was donate successfully");
    }

    @GetMapping("/get-all-charities")
    @Operation(
            summary = "Get All Charities",
            description = "Retrieves a list of all charities."
    )
    @PermitAll
    public CollectionModel<CharityDefaultResponse> getAllCharities() {
        List<CharityDefaultResponse> charityResponse = charityService.charityResponse();

        CollectionModel<CharityDefaultResponse> collectionModel = CollectionModel.of(charityResponse);

        collectionModel.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CharityApi.class)
                .donateToCharity(null, 0.0)).withRel("donate"));

        return collectionModel;
    }
}