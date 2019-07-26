package com.softpager.cms.controllers;


import com.softpager.cms.abstracts.CMSUser;
import com.softpager.cms.entities.Course;
import com.softpager.cms.entities.Role;
import com.softpager.cms.entities.Student;
import com.softpager.cms.services.CMSUserService;
import com.softpager.cms.services.CourseService;
import com.softpager.cms.services.StudentService;

import com.softpager.cms.utils.UserHelper;
import com.softpager.cms.utils.ErrorMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;


@Slf4j
@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private CMSUserService userService;

    @Autowired
    private CourseService courseService;

   @Autowired
    private UserHelper userHelper;


    //Fetching all existing students from the database.
    @GetMapping
    public String getStudents(Model model, @RequestParam(defaultValue = "0") int page) {
        Page<Student> allStudents = studentService.getStudents(PageRequest.of(page, 8));
        model.addAttribute("students", allStudents);
        model.addAttribute("currentPage", page);
        return "student/students";
    }


    //This method shows the form to create new student
    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("student", new Student());
        return "/student/add-student";
    }


    // This method create a new student
    @PostMapping("/create")
    public String createStudent(@ModelAttribute("student") Student theStudent) {
        studentService.saveStudent(theStudent);
        return "redirect:/students";
    }

    @GetMapping("/details")
    public String getStudent(@RequestParam("userEmail") String email, Model model){
        CMSUser theUser = userService.findByEmail(email);
        model.addAttribute("student", theUser);
        model.addAttribute("courses", theUser.getCourses());
        return "student/student-details";
    }

    // This method updates an existing student
    @GetMapping("/update")
    public String update(@RequestParam("userId") long theId, Model model) {
        Optional<CMSUser> theStudent = userService.getUser(theId);
        model.addAttribute("student", theStudent);
        return "/student/add-student";
    }


    //This method deletes  a student by the ID
    @GetMapping("/delete")
    public String deleteStudent(@RequestParam("userEmail") String email,
                         Principal principal, Model model) {
        if (userHelper.getCurrentUser(principal, email)) {
            userHelper.deleteUser(email);
                return "redirect:/students";
    }else {
        model.addAttribute("errorMessage", ErrorMessage.UNAUTHORIZED_OPERATION);
        model.addAttribute("goBack", ErrorMessage.GO_BACK);
    }
        return "error-page";
    }
}
