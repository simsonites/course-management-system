package com.softpager.cms.controllers;

import com.softpager.cms.entities.Student;
import com.softpager.cms.services.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Slf4j
@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;


    //Fetching all existing students from the database.
    @GetMapping
    public String getStudents(Model model, @RequestParam(defaultValue = "0") int page) {
        Page<Student> allStudents = studentService.getStudents(PageRequest.of(page, 8));
        log.info("The students: {}", allStudents);
        model.addAttribute("students", allStudents);
        model.addAttribute("currentPage", page);
        return "student/students";
    }

    //Fetching a single existing students from the database by the ID.
    @GetMapping("/student")
    public String getStudent(@RequestParam("userEmail") String email, Model model) {
        Student theStudent = studentService.getStudent(email);
        model.addAttribute("student", theStudent);
        return "student/student-profile";
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

    // This method updates an existing student
    @GetMapping("/update")
    public String update(@RequestParam("userEmail") String email, Model model) {
        Student theStudent = studentService.getStudent(email);
        model.addAttribute("student", theStudent);
        return "/student/add-student";
    }

    //This method deletes  a student by the ID
    @GetMapping("/delete")
    public String delete(@RequestParam("userEmail") String email) {
        studentService.delete(email);
        return "redirect:/students";
    }

}
