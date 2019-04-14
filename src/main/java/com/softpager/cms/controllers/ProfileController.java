package com.softpager.cms.controllers;

import com.softpager.cms.abstracts.User;
import com.softpager.cms.entities.Student;
import com.softpager.cms.services.CourseService;
import com.softpager.cms.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courseService;

    @GetMapping()
    public String getUserProfile(Model model, Principal principal){
        String userEmail = principal.getName();
        User theUser = userService.getUser(userEmail);
        if (theUser instanceof Student) {
            model.addAttribute("courses", courseService.getStudentCourses(theUser.getEmail()));
        }
        else {
            model.addAttribute("courses", courseService.getInstructorCourses(theUser.getEmail()));
        }
        return "user/profile";
    }
}
