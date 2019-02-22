package com.softpager.cms.services;

import com.softpager.cms.entities.Course;
import com.softpager.cms.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;



@Service
public class CourseServiceImpl implements CourseService{

    private CourseRepository courseRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Page<Course> getCourses(PageRequest pageRequest) {
        return courseRepository.findAll(pageRequest);
    }
}
