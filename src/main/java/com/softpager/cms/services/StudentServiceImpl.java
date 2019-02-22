package com.softpager.cms.services;

import com.softpager.cms.entities.Student;
import com.softpager.cms.entities.StudentPhoto;
import com.softpager.cms.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService{

    private StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Page<Student> getStudents(PageRequest pageRequest) {
        return studentRepository.findAll(pageRequest);
    }

    @Override
    public Student getStudent(long theId) {
        Optional<Student> theStudent = studentRepository.findById(theId);
        return theStudent.orElse(null);
    }

    @Override
    public void save(Student theStudent) {
        studentRepository.save(theStudent);
    }

    @Override
    public void delete(long theId) {
        studentRepository.deleteById(theId);
    }



}
