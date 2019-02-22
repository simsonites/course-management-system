package com.softpager.cms.controllers;

import com.softpager.cms.entities.Course;
import com.softpager.cms.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/courses")
public class CourseController {

    private CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public String getCourses(Model model, @RequestParam(defaultValue = "0") int page){
        Page<Course> allCourses = courseService.getCourses(PageRequest.of(page, 5));
        model.addAttribute("course", allCourses);
        model.addAttribute("currentPage", page);
        return "courses/courses";
    }
}
