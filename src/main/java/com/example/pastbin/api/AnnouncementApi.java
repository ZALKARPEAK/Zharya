package com.example.pastbin.api;

import com.example.pastbin.api.response.CustomResponse;
import com.example.pastbin.dto.Announcement.AddAnnouncementRequest;
import com.example.pastbin.dto.Announcement.GetAnnouncementByIdResponse;
import com.example.pastbin.dto.Announcement.UpdateAnnouncementRequest;
import com.example.pastbin.service.AnnouncementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/announcement")
@CrossOrigin
@Tag(name = "Announcement api", description = "Announcement endpoints")
public class AnnouncementApi {
    private final AnnouncementService announcementService;

    @PostMapping("/create-ads")
    @Operation(
            summary = "Create Announcement",
            description = "Creates a new announcement with the provided details in the request body. The request must include fields such as title, description, category, subcategory, and other necessary information. Only authenticated users with the 'USER' or 'ADMIN' role are allowed to create announcements."
    )
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public CustomResponse<?> addAnnouncement(@RequestBody AddAnnouncementRequest request) {
        announcementService.addAnnouncement(request);
        return new CustomResponse<>(HttpStatus.OK, "Announcement saved successfully");
    }

    @GetMapping("/get-all-categories")
    @Operation(
            summary = "Get All Categories and Subcategories",
            description = "Retrieves a map containing all the available categories and their corresponding subcategories. This endpoint can be accessed by any user, regardless of their role."
    )
    @PermitAll
    public ResponseEntity<Map<String, Object>> getAllCategories() {
        Map<String, Object> response = announcementService.getAllCategories();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/edit-announcement")
    @Operation(
            summary = "Edit Announcement",
            description = "Updates an existing announcement with the provided ID and the new details in the request body. Only authenticated users with the 'USER' or 'ADMIN' role are allowed to edit announcements."
    )
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public CustomResponse<?> editAnnouncement(@Valid @RequestParam Long announcementId,
                                              @RequestBody UpdateAnnouncementRequest request) {
        announcementService.editAnnouncement(announcementId, request);
        return new CustomResponse<>(HttpStatus.OK, "Edit was successful");
    }

    @DeleteMapping("/delete-announcement")
    @Operation(
            summary = "Delete Announcement",
            description = "Deletes an existing announcement with the provided ID. Only authenticated users with the 'USER' or 'ADMIN' role are allowed to delete announcements."
    )
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public CustomResponse<?> removeAnnouncement(@RequestParam Long announcementId) {
        announcementService.removeAnnouncement(announcementId);
        return new CustomResponse<>(HttpStatus.OK, "Announcement was deleted successfully");
    }

    @GetMapping("/get-announcement-by-id")
    @Operation(
            summary = "Get Announcement by ID",
            description = "Retrieves an announcement by its unique identifier. " +
                    "The ID of the announcement to be fetched is provided as a request parameter."
    )
    @PermitAll
    public GetAnnouncementByIdResponse getAnnouncementByIdResponse(@RequestParam Long announcementId) {
        return announcementService.getAnnouncementById(announcementId);
    }
}