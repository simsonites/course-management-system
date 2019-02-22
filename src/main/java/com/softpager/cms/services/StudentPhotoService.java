package com.softpager.cms.services;

import com.softpager.cms.entities.StudentPhoto;
import org.springframework.web.multipart.MultipartFile;

public interface StudentPhotoService {
    StudentPhoto saveStudentPhoto(MultipartFile file);

    StudentPhoto getStudentPhoto(long fileId);
}
