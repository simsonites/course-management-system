package com.softpager.cms.services;

import com.softpager.cms.entities.FileUpload;
import com.softpager.cms.exceptions.FileStorageException;
import com.softpager.cms.exceptions.ResourceNotFoundException;
import com.softpager.cms.repositories.FileUploadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileUploadServiceImpl implements FileUploadService {


    private FileUploadRepository fileUploadRepository;

    @Autowired
    public FileUploadServiceImpl(FileUploadRepository fileUploadRepository) {
        this.fileUploadRepository = fileUploadRepository;
    }

    @Override
    public FileUpload saveUserPhoto(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }
            FileUpload theFile = new FileUpload(fileName, file.getContentType(), file.getBytes());
            return fileUploadRepository.save(theFile);
        } catch (IOException ex) {
            throw new FileStorageException("Could not save file " + fileName + ". Please try again!", ex);
        }
    }


    @Override
    public FileUpload getUserPhoto(long fileId) {
        return fileUploadRepository.findById(fileId)
                .orElseThrow(() -> new ResourceNotFoundException("File not found with id " + fileId));
    }

}
