package com.softpager.cms.services;

import com.softpager.cms.entities.StudentPhoto;
import com.softpager.cms.exceptions.FileStorageException;
import com.softpager.cms.exceptions.MyFileNotFoundException;
import com.softpager.cms.repositories.StudentPhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class StudentPhotoServiceImpl implements StudentPhotoService {


    private StudentPhotoRepository studentPhotoRepository;

    @Autowired
    public StudentPhotoServiceImpl(StudentPhotoRepository studentPhotoRepository) {
        this.studentPhotoRepository = studentPhotoRepository;
    }

    @Override
    public StudentPhoto saveStudentPhoto(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }
            StudentPhoto theFile = new StudentPhoto(fileName, file.getContentType(), file.getBytes());
            return studentPhotoRepository.save(theFile);
        }
         catch (IOException ex) {
            throw new FileStorageException("Could not save file " + fileName + ". Please try again!", ex);
        }
    }
    @Override
    public StudentPhoto getStudentPhoto(long fileId) {
        return studentPhotoRepository.findById(fileId)
                .orElseThrow(() -> new MyFileNotFoundException("File not found with id " + fileId));
    }

}
