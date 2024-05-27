package com.example.pastbin.api;

import com.example.pastbin.api.response.CustomResponse;
import com.example.pastbin.dto.Announcement.AddAnnouncementRequest;
import com.example.pastbin.dto.Announcement.UpdateAnnouncementRequest;
import com.example.pastbin.service.AnnouncementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @Operation(summary = "Create Announcement", description = "Create new announcement")
    public CustomResponse<?> addAnnouncement(@RequestBody AddAnnouncementRequest request) {
        announcementService.addAnnouncement(request);
        return new CustomResponse<>(HttpStatus.OK, "Announcement save successfully");
    }

    @GetMapping("/get-all-categories")
    @Operation(description = "Get all categories and subcategories")
    public ResponseEntity<Map<String, Object>> getAllCategories() {
        Map<String, Object> response = announcementService.getAllCategories();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/edit-announcement")
    @Operation(summary = "Edit Announcement", description = "Edit announcement by id")
    public CustomResponse<?> editAnnouncement(@Valid @RequestParam Long announcementId,
                                              @RequestBody UpdateAnnouncementRequest request) {
        announcementService.editAnnouncement(announcementId, request);
        return new CustomResponse<>(HttpStatus.OK, "Edit was successfully");
    }

    @DeleteMapping("/delete-announcement")
    @Operation(summary = "Delete Announcement", description = "Delete announcement by id")
    public CustomResponse<?> deleteAnnouncement(@RequestParam Long announcementId) {
        announcementService.removeAnnouncement(announcementId);
        return new CustomResponse<>(HttpStatus.OK, "Announcement was successfully");
    }
}