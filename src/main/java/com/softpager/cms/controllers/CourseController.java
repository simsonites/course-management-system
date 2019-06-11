package com.softpager.cms.controllers;

import com.softpager.cms.entities.Course;
import com.softpager.cms.entities.Student;
import com.softpager.cms.exceptions.MyFileNotFoundException;
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
import java.util.Collections;
import java.util.List;
import java.util.Set;


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
    @GetMapping("")
    public String getCourses(Model model, @RequestParam(defaultValue = "0") int page) {
        Page<Course> allCourses = courseService.getCourses(PageRequest.of(page, 5));
        model.addAttribute("courses", allCourses);
        model.addAttribute("currentPage", page);
        return "home";
    }

    @GetMapping("/course")
    public String getCourse(@RequestParam("courseId") long theId, Model model) {
        Course theCourse = courseService.getCourse(theId);
        model.addAttribute("course", theCourse);
        model.addAttribute("courses", this.getAllCourses());
        return "course/course-detail";
    }

    private List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }


    @RequestMapping("/add-user-course")
    public String registerForCourse(@RequestParam("courseId") long theId, Principal principal,
                                    HttpSession httpSession, Model model) {
        Course theCourse = courseService.getCourse(theId);
        String email = principal.getName();
        List<Student> students = theCourse.getStudents();
        for (Student studentsInCourse : students) {
            if (studentsInCourse.getEmail().equals(email)) {
                model.addAttribute("errorMessage", "Sorry , a student with this email :  "
                        + email + "  is already registered in this course");
                return "error-page";
            }
        }
        httpSession.setAttribute("email", email);
        courseService.addCourseForStudent(theCourse, studentService.getStudent(email));
        model.addAttribute("userEmail", email);
        return "redirect:/students/student";
    }


}
