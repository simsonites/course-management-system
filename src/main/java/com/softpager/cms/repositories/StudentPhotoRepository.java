package com.softpager.cms.repositories;

import com.softpager.cms.entities.StudentPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentPhotoRepository extends
        JpaRepository<StudentPhoto, Long> {
}
