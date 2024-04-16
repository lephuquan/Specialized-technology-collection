package com.lpq.savefilefolder.repositories;

import com.lpq.savefilefolder.entities.RelatedFile;
import com.lpq.savefilefolder.entities.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RelatedFileRepository extends JpaRepository<RelatedFile, Long> {

    void deleteByUserProfile(UserProfile userProfile);
}
