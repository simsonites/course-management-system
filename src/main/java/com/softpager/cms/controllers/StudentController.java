package com.softpager.cms.controllers;

import com.softpager.cms.abstracts.AbstractUser;
import com.softpager.cms.entities.Course;
import com.softpager.cms.entities.Instructor;
import com.softpager.cms.entities.Student;
import com.softpager.cms.services.StudentService;
import com.softpager.cms.services.UserService;
import com.softpager.cms.utils.CurrentUser;
import com.softpager.cms.utils.ErrorMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;
import java.util.Set;


@Slf4j
@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private UserService userService;

   @Autowired
    private CurrentUser currentUser;


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
        AbstractUser theUser = userService.getUser(email);
        model.addAttribute("student", theUser);
        model.addAttribute("courses", theUser.getCourses());
        return "student/student-details";
    }

    // This method updates an existing student
    @GetMapping("/update")
    public String update(@RequestParam("userEmail") String email, Model model) {
        Student theStudent = studentService.getStudent(email);
        model.addAttribute("student", theStudent);
        return "/student/add-student";
    }


    //This method deletes  a student by the ID
    @GetMapping("/delete")
    public String delete(@RequestParam("userEmail") String email,
                         Principal principal, Model model) {
        if (currentUser.getCurrentUser(principal, email)){
            userService.delete(email);
            return "redirect:/students";
    }else {
        model.addAttribute("errorMessage", ErrorMessage.UNAUTHORIZED_OPERATION);
        model.addAttribute("goBack", ErrorMessage.GO_BACK);
    }
        return "error-page";
    }

}
