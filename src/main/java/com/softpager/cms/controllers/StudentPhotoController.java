package com.softpager.cms.controllers;

import com.softpager.cms.entities.Student;
import com.softpager.cms.entities.StudentPhoto;
import com.softpager.cms.services.StudentPhotoService;
import com.softpager.cms.services.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Controller
@RequestMapping("/photo")
public class StudentPhotoController {


    private StudentService studentService;
    private StudentPhotoService studentPhotoService;

    @Autowired
    public StudentPhotoController(StudentService studentService,
                                  StudentPhotoService studentPhotoService) {
        this.studentService = studentService;
        this.studentPhotoService = studentPhotoService;
    }

    @GetMapping("/form")
    public String getUploadForm(@RequestParam("studentId") Student student, Model model) {
        Student theStudent = studentService.getStudent(student.getId());
        log.info("The student ID is : {} " + theStudent.getId());
        model.addAttribute("student", theStudent);
        return "upload/upload-photo";
    }

    private StudentPhoto uploadFile(@RequestParam("studentId") Student student,
                                    @RequestParam("file") MultipartFile photo) {
        Student theStudent = studentService.getStudent(student.getId());
        StudentPhoto studentPhoto = studentPhotoService.saveStudentPhoto(photo);
        theStudent.setPhoto(studentPhoto);
        studentService.save(theStudent);
        return studentPhoto;
    }

    @PostMapping("/upload")
    public String updateUserPhoto(@RequestParam("studentId") long theId, MultipartFile file){
        Student theStudent = studentService.getStudent(theId);
        if(theStudent != null){
            theStudent.setPhoto(this.uploadFile(theStudent,file));
        }
        return "redirect:/students";
    }
}
