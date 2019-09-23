package com.softpager.cms.services;

import com.softpager.cms.abstracts.AbstractUser;
import com.softpager.cms.entities.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Set;

public interface CourseService {
    Page<Course> getCourses(PageRequest pageRequest);


    void saveCourse(Course theCourse);

    Course getCourse(long theId);

    void deleteCourse(long theId);

    List<Course> getAllCourses();

    void breakUserRelationship(Course theCourse, AbstractUser theUser);

    List<Course> findByTitle(String theTitle);

    void addUserToCourse(Course theCourse, AbstractUser theStudent);

    void removeUserFromCourse(Course theCourse, AbstractUser user);


    List<Course> getSelectedCourses(long[] theId);

}
