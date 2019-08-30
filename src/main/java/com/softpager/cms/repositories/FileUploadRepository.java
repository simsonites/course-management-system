package com.softpager.cms.repositories;

import com.softpager.cms.entities.FileUpload;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileUploadRepository extends
        JpaRepository<FileUpload, Long> {
}
