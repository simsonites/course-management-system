package com.softpager.cms.services;

import com.softpager.cms.abstracts.AbstractUser;
import com.softpager.cms.entities.Course;
import com.softpager.cms.entities.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface CourseService {

    void saveCourse(Course theCourse);

    Course getCourse(long theId);

    void deleteCourse(long theId);

    List<Course> getAllCourses();

    Page<Course> findByTitle(String theTitle, PageRequest page);

    void addUserToCourse(Course theCourse, AbstractUser theStudent);


    List<Course> getSelectedCourses(long[] theId);

    Object manageCourse(String title);

    Course findCourseByTitle(String cTitle);

    void saveAll(List<Course> coursesToAdd, AbstractUser theUser);

    void removeCourse(Course theCourse, AbstractUser user);

}
