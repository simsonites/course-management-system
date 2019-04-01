package com.softpager.cms.services;

import com.softpager.cms.entities.UserPhoto;
import org.springframework.web.multipart.MultipartFile;

public interface UserPhotoService {
    UserPhoto saveUserPhoto(MultipartFile file);

    UserPhoto getUserPhoto(long fileId);
}
