package com.softpager.cms.services;

import com.softpager.cms.abstracts.AbstractUser;
import com.softpager.cms.entities.Course;
import com.softpager.cms.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    private Course course;


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
    public void addUserToCourse(Course theCourse, AbstractUser theUser) {
       theCourse.addUserToCourse(theUser);
        courseRepository.save(theCourse);
    }

    @Override
    public void removeUserFromCourse(Course theCourse, AbstractUser theUser) {
       theCourse.removeUserFromCourse(theUser);
        courseRepository.delete(theCourse);
    }


    @Override
    public Page<Course> findByTitle(String theTitle, PageRequest page) {
        return courseRepository.findByTitleLike("%"+theTitle+"%", page);
    }

    @Override
    public List<Course> getSelectedCourses(long[] theIds) {
        return courseRepository.getCoursesByIdIn(theIds);
    }


    @Override
    public Object manageCourse(String title) {
        return courseRepository.findByTitleLike("%"+title+"%");
    }
}
