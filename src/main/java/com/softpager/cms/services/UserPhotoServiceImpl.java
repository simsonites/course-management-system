package com.softpager.cms.services;

import com.softpager.cms.entities.UserPhoto;
import com.softpager.cms.exceptions.FileStorageException;
import com.softpager.cms.exceptions.MyFileNotFoundException;
import com.softpager.cms.repositories.UserPhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class UserPhotoServiceImpl implements UserPhotoService {


    private UserPhotoRepository userPhotoRepository;

    @Autowired
    public UserPhotoServiceImpl(UserPhotoRepository studentPhotoRepository) {
        this.userPhotoRepository = studentPhotoRepository;
    }

    @Override
    public UserPhoto saveUserPhoto(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }
            UserPhoto theFile = new UserPhoto(fileName, file.getContentType(), file.getBytes());
            return userPhotoRepository.save(theFile);
        }
         catch (IOException ex) {
            throw new FileStorageException("Could not save file " + fileName + ". Please try again!", ex);
        }
    }
    @Override
    public UserPhoto getUserPhoto(long fileId) {
        return userPhotoRepository.findById(fileId)
                .orElseThrow(() -> new MyFileNotFoundException("File not found with id " + fileId));
    }

}
