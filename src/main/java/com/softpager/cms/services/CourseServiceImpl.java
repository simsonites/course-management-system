package com.softpager.cms.services;

import com.softpager.cms.entities.Course;
import com.softpager.cms.entities.Student;
import com.softpager.cms.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CourseServiceImpl implements CourseService{

    private CourseRepository courseRepository;

    private StudentService studentService;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository,
                             StudentService studentService) {
        this.courseRepository = courseRepository;
        this.studentService = studentService;
    }

    @Override
    public Page<Course> getCourses(PageRequest pageRequest) {
        return courseRepository.findAll(pageRequest);
    }

    @Override
    public void saveCourse(Course theCourse) {
        courseRepository.save(theCourse);
    }

    @Override
    public Course getCourse(long theId) {
        Optional<Course> theCourse = courseRepository.findById(theId);
        return theCourse.orElse(null);
    }

    @Override
    public void deleteCourse(long theId) {
        courseRepository.deleteById(theId);
    }

    @Override
    public void addCourseForStudent(Course theCourse, Student theStudent) {
        theCourse.getListOfStudents().add(theStudent);
        courseRepository.save(theCourse);
    }

    @Override
    public List<Course> getStudentCourses(String studentEmail) {
        Student theStudent = studentService.getStudent(studentEmail);
        return theStudent.getCourses();
    }
}
