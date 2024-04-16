package com.lpq.savefilefolder.services;

import com.lpq.savefilefolder.payload.UserProfileRequest;

public interface IUserProfileService {

    String createUserProfile(UserProfileRequest userProfileRequest);

    String updateUserProfile(UserProfileRequest userProfileRequest, Long id);
}
