package com.softpager.cms.services;

import com.softpager.cms.entities.Course;
import com.softpager.cms.entities.Instructor;
import com.softpager.cms.entities.Student;
import com.softpager.cms.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
public class CourseServiceImpl implements CourseService{

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentService studentService;

    @Autowired
    private InstructorService instructorService;

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
        theCourse.getStudents().add(theStudent);
        courseRepository.save(theCourse);
    }

    @Override
    public List<Course> getStudentCourses(String studentEmail) {
        Student theStudent = studentService.getStudent(studentEmail);
        return theStudent.getCourses();
    }

    @Override
    public Set<Course> getInstructorCourses(String instructorEmail) {
        Instructor theInstructor = instructorService.getInstructor(instructorEmail);
        return theInstructor.getCourses();
    }
}
