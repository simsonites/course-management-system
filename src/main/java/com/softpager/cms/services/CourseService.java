package com.softpager.cms.services;

import com.softpager.cms.entities.Course;
import com.softpager.cms.entities.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Set;

public interface CourseService {
    Page<Course> getCourses(PageRequest pageRequest);

    void saveCourse(Course theCourse);

    void addCourseForStudent(Course theCourse, Student theStudent);

    Course getCourse(long theId);

    void deleteCourse(long theId);

    List<Course> getStudentCourses(String studentEmail);

    Set<Course> getInstructorCourses(String instructorEmail);
}
