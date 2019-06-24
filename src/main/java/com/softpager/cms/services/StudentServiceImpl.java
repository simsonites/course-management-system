package com.softpager.cms.services;

import com.softpager.cms.entities.Course;
import com.softpager.cms.entities.Role;
import com.softpager.cms.entities.Student;
import com.softpager.cms.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @Override
    public Page<Student> getStudents(PageRequest pages) {
        return studentRepository.findAll(pages);
    }

    @Override
    public Student getStudent(String email) {
        Optional<Student> theStudent = studentRepository.findById(email);
        return theStudent.orElse(null);
    }

    @Override
    public void saveStudent(Student theStudent) {
        theStudent.setPassword(passwordEncoder.encode(theStudent.getPassword()));
        Role newRole = new Role("STUDENT");
        Set<Role> studentRoles = new HashSet<>();
        studentRoles.add(newRole);
        theStudent.setRoles(studentRoles);
        studentRepository.save(theStudent);
    }


    @Override
    public void delete(String email) {
        studentRepository.deleteById(email);
    }


    @Override
    public List<Student> getListOfSelectedStudents(boolean containsAll) {
        return null;
    }

}
