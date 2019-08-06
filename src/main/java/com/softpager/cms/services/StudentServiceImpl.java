package com.softpager.cms.services;

import com.softpager.cms.entities.Course;
import com.softpager.cms.entities.Role;
import com.softpager.cms.entities.Student;
import com.softpager.cms.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;


    @Autowired
    private RoleService roleService;

    private Course course = new Course();



    @Override
    public Page<Student> getStudents(PageRequest pages) {
        return studentRepository.findAll(pages);
    }

    @Override
    public void saveStudent(Student theStudent) {
        Role newRole = roleService.findByName("USER");
        theStudent.setRole(newRole);
        studentRepository.save(theStudent);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student findByEmail(String email) {
        return studentRepository.findByAccount_Email(email);
    }

    @Override
    public void deleteByEmail(String email) {
        course.removeStudentFromCourse(studentRepository.findByAccount_Email(email));
        studentRepository.deleteById(studentRepository.findByAccount_Email(email).getId());
    }

    @Override
    public Student findByAccount_Email(String email) {
        return null;
    }

    @Override
    public Optional<Student> findById(long theId) {
        return studentRepository.findById(theId);
    }
}
