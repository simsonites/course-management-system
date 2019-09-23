/*
package com.softpager.cms.utils;

import com.softpager.cms.entities.Course;
import com.softpager.cms.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class CourseConverter  implements Converter<String, Course> {

    @Autowired
    private CourseService courseService;

    @Override
    public Course convert(String id) {
        List<Course> selectedCourses = courseService.getAllCourses();
        int parseId = Integer.parseInt(id);
        int index = parseId-1;
        return selectedCourses.get(index);
    }
}
*/
