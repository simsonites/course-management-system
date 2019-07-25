package com.softpager.cms.services;

import com.softpager.cms.entities.Course;
import com.softpager.cms.entities.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;


public interface StudentService {

    Page<Student> getStudents(PageRequest pageRequest);


    void saveStudent(Student theStudent);

    List<Student> getAllStudents();

}
