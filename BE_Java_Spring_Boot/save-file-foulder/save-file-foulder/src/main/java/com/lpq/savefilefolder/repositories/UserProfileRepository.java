package com.lpq.savefilefolder.repositories;

import com.lpq.savefilefolder.entities.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
}
