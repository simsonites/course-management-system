package com.softpager.cms.repositories;

import com.softpager.cms.entities.UserPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPhotoRepository extends
        JpaRepository<UserPhoto, Long> {
}
