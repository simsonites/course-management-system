package com.softpager.cms.services;

import com.softpager.cms.abstracts.CMSUser;
import com.softpager.cms.entities.Course;
import com.softpager.cms.entities.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface CourseService {
    Page<Course> getCourses(PageRequest pageRequest);


    void saveCourse(Course theCourse);

    Course getCourse(long theId);

    void deleteCourse(long theId);

    List<Course> getAllCourses();

    List<Course> findByTitle(String theTitle);

    void addUserToCourse(Course theCourse, Student theStudent);

    void removeUserFromCourse(Course theCourse, Student theStudent);


    List<Course> getSelectedCourses(long[] theId);

}
