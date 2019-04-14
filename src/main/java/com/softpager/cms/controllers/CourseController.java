package com.softpager.cms.controllers;

import com.softpager.cms.entities.Course;
import com.softpager.cms.services.CourseService;
import com.softpager.cms.services.StudentService;
import com.softpager.cms.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.Principal;


@Slf4j
@Controller
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private UserService userService;

    @Autowired
    private StudentService studentService;


    //This method will get all courses from the database.
    @GetMapping()
    public String getCourses(Model model, @RequestParam(defaultValue = "0") int page){
        Page<Course> allCourses = courseService.getCourses(PageRequest.of(page, 5));
        model.addAttribute("courses", allCourses);
        model.addAttribute("currentPage", page);
        return "course/courses";
    }

    @GetMapping("/course")
    public String getCourse(@RequestParam("courseId") long theId, Model model){
        Course theCourse = courseService.getCourse(theId);
        model.addAttribute("course", theCourse);
        return "course/course-detail";
    }

    //This method show the form to create new courses
    @GetMapping("/new")
    public String showForm(Model model){
        Course course = new Course();
        model.addAttribute("course", course);
        return "course/add-course";
    }

    //This method actually saves the course to the database
    @PostMapping("/create")
    public String createCourse(@ModelAttribute("course") Course theCourse){
        courseService.saveCourse(theCourse);
        return "redirect:/courses";
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

    @RequestMapping("/add-user-course")
    public String registerForCourse(@RequestParam("courseId") long theId, Principal principal,
                                    HttpSession httpSession, Model model){
        Course theCourse = courseService.getCourse(theId);
        String email =  principal.getName();
         httpSession.setAttribute("email", email);
        courseService.addCourseForStudent(theCourse,studentService.getStudent(email));
        model.addAttribute("userEmail", email);
        return "redirect:/students/student";

    }

}
