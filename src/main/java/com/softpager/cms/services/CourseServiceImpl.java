package com.softpager.cms.services;

import com.softpager.cms.abstracts.CMSUser;
import com.softpager.cms.entities.Course;
import com.softpager.cms.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;


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
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public void addUserToCourse(Course theCourse, CMSUser theUser) {
       theCourse.addUserToCourse(theUser);
        courseRepository.save(theCourse);
    }

    @Override
    public void removeUserFromCourse(Course theCourse, CMSUser theUser) {
       theCourse.removeUserFromCourse(theUser);
        courseRepository.delete(theCourse);
    }


    @Override
    public List<Course> findByTitle(String theTitle) {
        return courseRepository.findByTitleLike("%" +theTitle+ "%");
    }

    @Override
    public List<Course> getSelectedCourses(long[] theIds) {
        return courseRepository.getCoursesByIdIn(theIds);
    }
}
