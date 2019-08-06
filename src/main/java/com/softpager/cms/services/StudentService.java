package com.softpager.cms.services;

import com.softpager.cms.entities.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;


public interface StudentService {

    Page<Student> getStudents(PageRequest pageRequest);


    void saveStudent(Student theStudent);

    List<Student> getAllStudents();

   Student findByEmail(String email);

    void deleteByEmail(String email);

    Student findByAccount_Email(String email);

    Optional<Student> findById(long theId);
}
