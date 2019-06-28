package com.softpager.cms.controllers;

import com.softpager.cms.abstracts.AbstractUser;
import com.softpager.cms.entities.Course;
import com.softpager.cms.entities.Student;
import com.softpager.cms.services.CourseService;
import com.softpager.cms.services.StudentService;
import com.softpager.cms.services.UserService;
import com.softpager.cms.utils.CurrentUser;
import com.softpager.cms.utils.ErrorMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.Principal;
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

    @Autowired
    private CurrentUser currentUser;


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
    public String registerForCourse(@RequestParam("courseId") long theId,
                                    Principal principal,
                                    HttpSession httpSession, Model model) {
        String email = principal.getName();
        Course theCourse = courseService.getCourse(theId);

        AbstractUser theStudent = userService.getUser(email);

        if ((theStudent != null) && theStudent.getCourses().size() == 3) {
            model.addAttribute("errorMessage", ErrorMessage.MAXIMUM_REGISTERED);
            model.addAttribute("goBackToProfile", ErrorMessage.GO_BACK_TO_PROFILE);
            return "error-page";
        }
        List<AbstractUser> students = theCourse.getUsers();
        for (AbstractUser studentsInCourse : students) {
            if (theStudent != null && studentsInCourse.getEmail().equals(theStudent.getEmail())) {
                model.addAttribute("errorMessage", " Sorry , " + email + "  "
                        + ErrorMessage.ALREADY_REGISTERED);
                model.addAttribute("goBack", ErrorMessage.GO_BACK_TO_COURSE);
                return "error-page";
            }
        }

        httpSession.setAttribute("email", email);
        courseService.addUserToCourse(theCourse, userService.getUser(email));
        model.addAttribute("userEmail", email);
        return "redirect:/students/details";
    }


    @RequestMapping("/remove-user-course")
    public String removeUserFromCourse(@RequestParam("courseId") long theId , Model model,
                                       HttpSession httpSession, Principal principal) {

        String email = principal.getName();
        Course theCourse = courseService.getCourse(theId);
        List<AbstractUser> students = theCourse.getUsers();
        for (AbstractUser studentsInCourse : students) {
            if (studentsInCourse.getEmail().equals(email)){
                httpSession.setAttribute("email", email);
                courseService.removeUserFromCourse(theCourse, userService.getUser(email));
                model.addAttribute("userEmail", email);
                return "redirect:/students/details";
            }
        }
        model.addAttribute("errorMessage", ErrorMessage.EMAIL_NOT_FOUND);
        model.addAttribute("goBackToProfile", ErrorMessage.GO_BACK_TO_PROFILE);
        return "error-page";
    }
}






