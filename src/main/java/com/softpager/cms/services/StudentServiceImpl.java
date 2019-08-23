package com.softpager.cms.services;

import com.softpager.cms.entities.Role;
import com.softpager.cms.entities.Student;
import com.softpager.cms.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Override
    public Page<Student> getStudents(PageRequest pages) {
        return studentRepository.findAll(pages);
    }

    @Override
    public Optional<Student> getStudent(long theId) {

        return studentRepository.findById(theId);
    }

    @Override
    public void saveStudent(Student theStudent) {
        theStudent.setPassword(passwordEncoder.encode(theStudent.getPassword()));
        Role newRole = roleService.findByName("STUDENT");
        Set<Role> studentRoles = new HashSet<>();
        studentRoles.add(newRole);
        theStudent.setRoles(studentRoles);
        studentRepository.save(theStudent);
    }


    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public List<Student> findByEmail(String email) {
        return studentRepository.findByEmailLike("%" +email+ "%");
    }
}
