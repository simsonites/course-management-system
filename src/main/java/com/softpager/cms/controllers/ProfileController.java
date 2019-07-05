package com.softpager.cms.controllers;

import com.softpager.cms.abstracts.AbstractUser;
import com.softpager.cms.entities.Course;
import com.softpager.cms.entities.Student;
import com.softpager.cms.services.CourseService;
import com.softpager.cms.services.StudentService;
import com.softpager.cms.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/user")
public class ProfileController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private UserService userService;



    @GetMapping("/profile")
    public String getStudentProfile(Model model, Principal principal) {
        String email = principal.getName();
        AbstractUser theUser = userService.getUser(email);
        if (theUser !=null){
            List<Course> userCourses = theUser.getCourses();
            model.addAttribute("user",theUser);
            model.addAttribute("courses", userCourses);
        }
        return "user-profile";
    }


 /*   @GetMapping("/details")
    public String userDetails(Model model, @RequestParam("userEmail") String email) {
        AbstractUser theUser = userService.getUser(email);
        if (theUser !=null){
            Set<Course> userCourses = theUser.getCourses();
            model.addAttribute("user",theUser);
            model.addAttribute("courses", userCourses);
        }
        return "user-profile";
    }*/



}
