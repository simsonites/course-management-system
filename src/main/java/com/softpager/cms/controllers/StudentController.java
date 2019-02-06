package com.softpager.cms.controllers;

import com.softpager.cms.entities.Student;
import com.softpager.cms.services.StudentService;
import com.softpager.cms.utils.CMSMapping;
import com.softpager.cms.utils.ViewNames;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@Controller
@RequestMapping("/students")
public class StudentController {

    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("")
    public String getStudents(Model model, @RequestParam(defaultValue="0")int page){
        Page<Student> allStudents = studentService.getStudents(PageRequest.of(page,5));
        log.info("The students: {}", allStudents);
        model.addAttribute("students", allStudents);
        model.addAttribute("currentPage", page);
        return "student/students";
    }

    @GetMapping("/student")
    public  String getStudent(@RequestParam long theId, Model model){
        Student theStudent = studentService.getStudent(theId);
        model.addAttribute("student", theStudent);
        return "student/student-profile";
    }

    @GetMapping("/new")
    public String showForm(Model model){
        Student newStudent = new Student();
        model.addAttribute("student", newStudent);
        return "/student/add-student";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("student") Student theStudent){
        studentService.save(theStudent);
        return "redirect:/students";
    }

    @DeleteMapping(CMSMapping.DELETE)
    public String delete(@RequestParam("studentId") long theId){
        studentService.delete(theId);
        return CMSMapping.REDIRECT;
    }

}
