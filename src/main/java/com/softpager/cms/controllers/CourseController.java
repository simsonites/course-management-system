package com.softpager.cms.controllers;

import com.softpager.cms.entities.Course;
import com.softpager.cms.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


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
        model.addAttribute("courses", allCourses);
        model.addAttribute("currentPage", page);
        return "course/courses";
    }

    @GetMapping("/new")
    public String showForm(Model model){
        Course course = new Course();
        model.addAttribute("course", course);
        return "course/add-course";
    }

    @PostMapping("/create")
    public String createCourse(@ModelAttribute("course") Course theCourse){
        courseService.saveCourse(theCourse);
        return "redirect:/courses";
    }

    @GetMapping("/course")
    public String getCourse(@RequestParam("courseId") long theId, Model model){
        Course theCourse = courseService.getCourse(theId);
        model.addAttribute("course", theCourse);
        return "course/course-detail";
    }

    //This method deletes a course from the database by the ID
    @GetMapping("/delete")
    public String deleteCourse(@RequestParam("courseId") long theId){
        courseService.deleteCourse(theId);
        return "redirect:/courses";
    }

    @GetMapping("/update")
    public String updateCourse(@RequestParam("courseId") long theId, Model model){
        Course theCourse = courseService.getCourse(theId);
        model.addAttribute("course", theCourse);
        return "course/add-course";
    }
}
