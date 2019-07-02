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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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


    @GetMapping
    public String manageCourses(Model model) {
        model.addAttribute("courses", this.getAllCourses());
        return "admin/manage-courses";
    }


    @GetMapping("/course")
    public String getCourse(@RequestParam("courseId") long theId, Model model) {
        Course theCourse = courseService.getCourse(theId);
        model.addAttribute("course", theCourse);
        model.addAttribute("courses", this.getAllCourses());
        model.addAttribute("student", new Student());
        return "course/course-detail";
    }


    @RequestMapping("/add-course-to-user")
    public String registerForCourse(@RequestParam("courseId") long theId,
                                        Principal principal, Model model,
                                    HttpSession httpSession) {
        String email = principal.getName();
        AbstractUser theUser = userService.getUser(email);
        Course theCourse = courseService.getCourse(theId);

        if ((theUser != null) && theUser.getCourses().size() == 3) {
            model.addAttribute("errorMessage", ErrorMessage.MAXIMUM_REGISTERED);
            model.addAttribute("goBackToProfile", ErrorMessage.GO_BACK_TO_PROFILE);
            return "error-page";
        }
        List<AbstractUser> users = theCourse.getUsers();
        for (AbstractUser usersInCourse : users) {
            if (theUser != null && usersInCourse.getEmail().equals(theUser.getEmail())) {
                model.addAttribute("errorMessage", " Sorry , " + email + "  "
                        + ErrorMessage.ALREADY_REGISTERED);
                model.addAttribute("goBack", ErrorMessage.GO_BACK_TO_COURSE);
                return "error-page";
            }
        }

        httpSession.setAttribute("email", email);
        if (theUser != null) {
            courseService.addUserToCourse(theCourse, userService.getUser(theUser.getEmail()));
        }
        model.addAttribute("userEmail", email);
        return "redirect:/students/details";
    }


    /*Here, we are using this method to get list of courses to  assign for a user
    (instructors or students*/
    @GetMapping("/get-courses")
    public String assignCourseToUserForm(@RequestParam("userEmail")String email,
                                         HttpSession httpSession){
        httpSession.setAttribute("email",email);
        return "redirect:/courses";
    }

    /*Here, we are using this method to assign multiple courses to a
       user  using the email*/
    @RequestMapping("/assign-multiple-courses")
    public String assignCourse(@RequestParam("courseId") long[] theId,
                               RedirectAttributes rd, HttpSession httpSession) {
        String theEmail = (String) httpSession.getAttribute("email");
        AbstractUser theUser = userService.getUser(theEmail);
        Set<Course> theCourses = courseService.getSelectedCourses(theId);
        if (theCourses != null){
            for (Course course : theCourses){
                if ((theUser !=null) && (!theUser.getCourses().contains(course)))
             courseService.addUserToCourse(course,theUser);
                rd.addFlashAttribute("message", "courses were successfully added to  "
                                    + (theUser != null ? theUser.getFirstName() : null));
                return "redirect:/admin";
            }
        }
        rd.addFlashAttribute("courseAlreadyAdded","Duplicate courses found detected for  "
                                     +theUser.getFirstName());
        return "admin/manage-courses";
    }



    private List<Course> getAllCourses() {
        return courseService.getAllCourses();
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






