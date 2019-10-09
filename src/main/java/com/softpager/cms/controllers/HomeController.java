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
import java.util.Optional;


@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private CourseService courseService;

    @GetMapping()
    public String homePage(Model model, @RequestParam(defaultValue = "0") int page,
                           @RequestParam Optional<String> title) {
        Page<Course> allCourses =  courseService.findByTitle(title.orElse("_"),PageRequest.of(page, 6));
        model.addAttribute("courses", allCourses);
        model.addAttribute("currentPage", page);
        model.addAttribute("student", new Student());
        return "course/courses";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }


    @GetMapping("/about")
    public String about(Model model) {
        return "about";
    }

}