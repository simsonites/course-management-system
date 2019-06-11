package com.softpager.cms.services;

import com.softpager.cms.entities.FileUpload;
import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
    FileUpload saveUserPhoto(MultipartFile file);

    FileUpload getUserPhoto(long fileId);
}
