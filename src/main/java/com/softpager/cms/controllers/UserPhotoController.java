package com.softpager.cms.controllers;

import com.softpager.cms.abstracts.User;
import com.softpager.cms.entities.Student;
import com.softpager.cms.entities.UserPhoto;
import com.softpager.cms.services.StudentService;
import com.softpager.cms.services.UserPhotoService;
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
public class UserPhotoController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserPhotoService userPhotoService;

    @Autowired
    private StudentService studentService;
    private Student student;

    // This method gets the form for the image upload
    @GetMapping("/form")
    public String getUploadForm(@RequestParam("email") User abUser,
                                                  Model model) {
        User theUser =userService.getUser(abUser.getEmail());
        log.info("The user email is : {} " + theUser.getFirstName());
        model.addAttribute("user", theUser);
        return "upload/upload-photo";
    }

    // this is the helper method for the image upload
    private UserPhoto uploadFile(@RequestParam("userEmail") User user,
                                 @RequestParam("file") MultipartFile photo) {
        User theUser = userService.getUser(user.getEmail());
        UserPhoto userPhoto = userPhotoService.saveUserPhoto(photo);
        theUser.setPhoto(userPhoto);
        userService.save(theUser);
        return userPhoto;
    }

    // this method saves the user photo to the database
    @PostMapping("/upload")
    public String updateUserPhoto(@RequestParam("email") User user, MultipartFile file) {
        User theUser = userService.getUser(user.getEmail());
        if (theUser != null) {
            log.info("This is the found User  {}", theUser.getFirstName());
            theUser.setPhoto(this.uploadFile(theUser, file));
        }
        if (theUser != null) {
            if (theUser.getClass().isInstance(student)) {
                return "redirect:/students";
            }
            else{
                return "redirect:/instructors";
            }
        }
        return "redirect:/";
    }

    //Fetching the the user photo from the database
    @GetMapping(value="/user/photo", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody
    byte[] getImageWithMediaType(@RequestParam("userEmail") String email)
            throws IOException {
        User theUser = userService.getUser(email);
        UserPhoto theUserPhoto = userPhotoService.getUserPhoto(theUser.getPhoto().getId());
        return theUserPhoto.getImage();
    }



}
