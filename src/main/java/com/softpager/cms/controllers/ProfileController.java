package com.softpager.cms.controllers;

import com.softpager.cms.entities.Course;
import com.softpager.cms.entities.Student;
import com.softpager.cms.entities.UserAccount;
import com.softpager.cms.services.StudentService;
import com.softpager.cms.services.CMSUserService;
import com.softpager.cms.services.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Set;

@Controller
@RequestMapping("/user")
public class ProfileController {


    @Autowired
    private CMSUserService cmsUserService;

    @Autowired
    private StudentService studentService;

    @Autowired
    public UserAccountService accountService;



    @GetMapping("/profile")
    public String getStudentProfile(Model model, Principal principal) {
        String email = principal.getName();
        UserAccount account = accountService.findByEmail(email);
        if (account !=null){
            Set<Course> userCourses = account.getUser().getCourses();
            model.addAttribute("user",account.getUser());
            model.addAttribute("courses", userCourses);
        }
        return "user-profile";
    }


}
