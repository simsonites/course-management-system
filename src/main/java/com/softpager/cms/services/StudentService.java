package com.softpager.cms.services;

import com.softpager.cms.entities.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Date;

public interface StudentService {
    Page<Student> getStudents(PageRequest pageRequest);

    Student getStudent(long theId);

    void save(Student theStudent);

    void delete(long theId);

}
