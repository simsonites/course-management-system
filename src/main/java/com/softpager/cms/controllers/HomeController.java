package com.softpager.cms.controllers;

import com.softpager.cms.entities.Course;
import com.softpager.cms.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;




@Controller
public class HomeController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/")
    public String homePage(Model model, @RequestParam(defaultValue = "0") int page) {
        Page<Course> allCourses = courseService.getCourses(PageRequest.of(page, 6));
        model.addAttribute("courses", allCourses);
        model.addAttribute("currentPage", page);
        return "home";
    }


    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

}
