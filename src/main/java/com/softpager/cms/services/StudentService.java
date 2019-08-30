package com.softpager.cms.services;

import com.softpager.cms.entities.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;


public interface StudentService {

    Page<Student> getStudents(PageRequest pageRequest);

  void saveStudent(Student theStudent);

    //void saveStudent(Student theStudent, String role);

    List<Student> getAllStudents();

    Optional<Student> getStudent(long theId);

    List<Student> findByEmail(String email);
}
