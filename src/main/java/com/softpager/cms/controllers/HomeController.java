package com.softpager.cms.controllers;

import com.softpager.cms.entities.Course;
import com.softpager.cms.entities.Student;
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
@RequestMapping("/")
public class HomeController {

    @Autowired
    private CourseService courseService;

    @GetMapping()
    public String homePage(Model model, @RequestParam(defaultValue = "0") int page) {
        Page<Course> allCourses = courseService.getCourses(PageRequest.of(page, 6));
        model.addAttribute("courses", allCourses);
        model.addAttribute("currentPage", page);
        model.addAttribute("student", new Student());
        return "course/courses";
    }


    @GetMapping("/search-course")
    public String searchCourse(@RequestParam(defaultValue = "") String theTitle, Model model){
        List<Course> foundCourses = courseService.findByTitle(theTitle);
        model.addAttribute("course", foundCourses);
        return "redirect: /";
    }


    @GetMapping("/about")
    public String about(Model model) {
        return "about";
    }

}