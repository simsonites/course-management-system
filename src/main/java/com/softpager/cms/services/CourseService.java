package com.softpager.cms.services;

import com.softpager.cms.entities.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface CourseService {
    Page<Course> getCourses(PageRequest pageRequest);
}
