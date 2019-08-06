package com.softpager.cms.controllers;

import com.softpager.cms.abstracts.CMSUser;
import com.softpager.cms.entities.FileUpload;
import com.softpager.cms.entities.UserAccount;
import com.softpager.cms.services.FileUploadService;
import com.softpager.cms.services.CMSUserService;
import com.softpager.cms.services.UserAccountService;
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
    private CMSUserService cmsUserService;

    @Autowired
    private FileUploadService fileUploadService;

    @Autowired
    private UserAccountService accountService;


    //This method gets the form for the image upload
    @GetMapping("/form")
    public String getUploadForm(@RequestParam("userEmail") String userEmail, Model model) {
        CMSUser theUser = cmsUserService.findByEmail(userEmail);
        model.addAttribute("user", theUser);
        return "upload/upload-photo";
    }

    // this method saves the user photo to the database
    @PostMapping("/upload")
    public String updateUserPhoto(@RequestParam("userEmail") String userEmail,
                                  MultipartFile file, Model model) {
        UserAccount account = accountService.findByEmail(userEmail);
        if (account != null) {
            account.getUser().setPhoto(this.uploadFile(account.getEmail(), file));
        }
        model.addAttribute("userEmail",account.getEmail());
        return "redirect:/user/profile";
    }

    // this is the helper method for the image upload
    private FileUpload uploadFile(@RequestParam("userEmail") String userEmail,
                                  @RequestParam("file") MultipartFile photo) {
        UserAccount account = accountService.findByEmail(userEmail);
        if (account != null) {
            FileUpload fileUpload = fileUploadService.saveUserPhoto(photo);
             account.getUser().setPhoto(fileUpload);
            cmsUserService.save(account.getUser());
            return fileUpload;
        }
        return null;
    }


    //Fetching the the user photo from the database
    @GetMapping(value = "/user/photo", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody
    byte[] getImageWithMediaType(@RequestParam("userEmail") String email)
            throws IOException {
        CMSUser theUser = cmsUserService.findByEmail(email);
        FileUpload theFileUpload = fileUploadService.getUserPhoto(theUser.getPhoto().getId());
        return theFileUpload.getImage();
    }


}
