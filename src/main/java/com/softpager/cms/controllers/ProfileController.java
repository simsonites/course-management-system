package com.softpager.cms.controllers;

import com.softpager.cms.abstracts.CMSUser;
import com.softpager.cms.entities.Course;
import com.softpager.cms.services.CourseService;
import com.softpager.cms.services.StudentService;
import com.softpager.cms.services.CMSUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/user")
public class ProfileController {


    @Autowired
    private CMSUserService cmsUserService;



    @GetMapping("/profile")
    public String getStudentProfile(Model model, Principal principal) {
        String email = principal.getName();
        CMSUser theUser = cmsUserService.findByEmail(email);
        if (theUser !=null){
            Set<Course> userCourses = theUser.getCourses();
            model.addAttribute("user",theUser);
            model.addAttribute("courses", userCourses);
        }
        return "user-profile";
    }


}
