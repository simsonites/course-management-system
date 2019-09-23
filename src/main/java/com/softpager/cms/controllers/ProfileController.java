package com.softpager.cms.controllers;

import com.softpager.cms.abstracts.AbstractUser;
import com.softpager.cms.entities.Course;
import com.softpager.cms.services.UserService;
import com.softpager.cms.utils.ViewNames;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;
import java.util.Set;

@Slf4j
@Controller
@RequestMapping("/user")
public class ProfileController {


    @Autowired
    private UserService userService;


    @GetMapping("/profile")
    public String getStudentProfile(Model model, Principal principal) {
        String email = principal.getName();
        AbstractUser theUser = userService.findByEmail(email);
        if (theUser ==null){
           return "/login";
        }
            Set<Course> userCourses = theUser.getCourses();
            model.addAttribute("user",theUser);
            model.addAttribute("courses", userCourses);
            return ViewNames.USER_PROFILE;
    }

}
