package com.softpager.cms.controllers;

import com.softpager.cms.entities.Student;
import com.softpager.cms.entities.StudentPhoto;
import com.softpager.cms.services.StudentPhotoService;
import com.softpager.cms.services.StudentService;import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@Slf4j
@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentPhotoService studentPhotoService;


    //Fetching all existing students from the database.
    @GetMapping
    public String getStudents(Model model, @RequestParam(defaultValue="0")int page){
        Page<Student> allStudents = studentService.getStudents(PageRequest.of(page,5));
        log.info("The students: {}", allStudents);
        model.addAttribute("students", allStudents);
        model.addAttribute("currentPage", page);
        return "student/students";
    }

    //Fetching a single existing students from the database by the ID.
    @GetMapping("/student")
    public  String getStudent(@RequestParam("studentId") long theId, Model model){
        Student theStudent = studentService.getStudent(theId);
        model.addAttribute("student", theStudent);
        return "student/student-profile";
    }

    //Fetching the student photo from the database
    @GetMapping(value="/student/photo", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImageWithMediaType(@RequestParam("studentId") long theId)
            throws IOException{
        Student theStudent = studentService.getStudent(theId);
        StudentPhoto theStudentPhoto = studentPhotoService.getStudentPhoto(theStudent.getPhoto().getId());
       return theStudentPhoto.getImage();
    }

   //This method shows the form to create new student
    @GetMapping("/new")
    public String showForm(Model model){
        Student newStudent = new Student();
        model.addAttribute("student", newStudent);
        return "/student/add-student";
    }

    // This method create a new student
    @PostMapping("/create")
    public String create(@ModelAttribute("student") Student theStudent){
        studentService.save(theStudent);
        return "redirect:/students";
    }

    // This method updates an existing student
    @GetMapping("/update")
    public String update(@RequestParam("studentId") long theId, Model model){
        Student theStudent = studentService.getStudent(theId);
        model.addAttribute("student", theStudent);
        return "/student/add-student";
    }

    //This method deletes  a student by the ID
    @GetMapping("/delete")
    public String delete(@RequestParam("studentId") long theId){
        studentService.delete(theId);
        return "redirect:/students";
    }

    //saving the student photo





}
