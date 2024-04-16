package com.lpq.savefilefolder.controllers;


import com.lpq.savefilefolder.payload.UserProfileRequest;
import com.lpq.savefilefolder.services.UserProfileServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileServiceImpl userProfileService;

    @PostMapping(value = "/create-user", consumes = {
            MediaType.MULTIPART_FORM_DATA_VALUE
    })
    public ResponseEntity<String> creatUserInfo(UserProfileRequest request) {
        return ResponseEntity.ok(userProfileService.createUserProfile(request));
    }

    @PutMapping(value = "/update-user/{userId}", consumes = {
            MediaType.MULTIPART_FORM_DATA_VALUE
    })
    public ResponseEntity<String> updateUserInfo(UserProfileRequest request, @PathVariable("userId") Long id) {
        return ResponseEntity.ok(userProfileService.updateUserProfile(request, id));
    }


}
