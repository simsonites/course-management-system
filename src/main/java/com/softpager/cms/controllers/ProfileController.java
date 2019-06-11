package com.softpager.cms.controllers;

import com.softpager.cms.entities.Student;
import com.softpager.cms.services.CourseService;
import com.softpager.cms.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class ProfileController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @GetMapping("/student")
    public String getStudentProfile(Model model, Principal principal) {
        String userEmail = principal.getName();
        Student theStudent = studentService.getStudent(userEmail);
        model.addAttribute("student", theStudent);
        return "student/student-profile";
    }
}
