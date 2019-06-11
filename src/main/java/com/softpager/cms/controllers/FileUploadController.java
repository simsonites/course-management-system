package com.softpager.cms.controllers;

import com.softpager.cms.abstracts.AbstractUser;
import com.softpager.cms.entities.Student;
import com.softpager.cms.entities.FileUpload;
import com.softpager.cms.services.StudentService;
import com.softpager.cms.services.FileUploadService;
import com.softpager.cms.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Controller
@RequestMapping("/photo")
public class FileUploadController {

    @Autowired
    private UserService userService;

    @Autowired
    private FileUploadService fileUploadService;

    @Autowired
    private StudentService studentService;
    private Student student;


    // This method gets the form for the image upload
    @GetMapping("/form")
    public String getUploadForm(@RequestParam("userEmail") String userEmail, Model model) {
        AbstractUser theUser = userService.getUser(userEmail);
        model.addAttribute("user", theUser);
        return "upload/upload-photo";
    }

    // this method saves the user photo to the database
    @PostMapping("/upload")
    public String updateUserPhoto(@RequestParam("userEmail") String userEmail,
                                  MultipartFile file) {
        AbstractUser theUser = userService.getUser(userEmail);
        if (theUser != null) {
            log.info("This is the found AbstractUser  {}", theUser.getFirstName());
            theUser.setPhoto(this.uploadFile(theUser, file));
        }
        return "redirect:/";
    }

    // this is the helper method for the image upload
    private FileUpload uploadFile(@RequestParam("userEmail") AbstractUser user,
                                  @RequestParam("file") MultipartFile photo) {
        AbstractUser theUser = userService.getUser(user.getEmail());
        FileUpload fileUpload = fileUploadService.saveUserPhoto(photo);
        theUser.setPhoto(fileUpload);
        userService.save(theUser);
        return fileUpload;
    }


    //Fetching the the user photo from the database
    @GetMapping(value = "/user/photo", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody
    byte[] getImageWithMediaType(@RequestParam("userEmail") String email)
            throws IOException {
        AbstractUser theUser = userService.getUser(email);
        FileUpload theFileUpload = fileUploadService.getUserPhoto(theUser.getPhoto().getId());
        return theFileUpload.getImage();
    }


}
