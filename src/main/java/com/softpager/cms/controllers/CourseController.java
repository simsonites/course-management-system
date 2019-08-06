package com.softpager.cms.controllers;

import com.softpager.cms.abstracts.CMSUser;
import com.softpager.cms.entities.Course;
import com.softpager.cms.entities.Student;
import com.softpager.cms.services.CourseService;
import com.softpager.cms.services.CMSUserService;
import com.softpager.cms.services.StudentService;
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


@Slf4j
@Controller
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private CMSUserService cmsUserService;

    @Autowired
    private StudentService studentService;


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


    @RequestMapping("/register-for-course")
    public String registerForCourse(@RequestParam("courseId") long theId,
                                        Principal principal, Model model,
                                    HttpSession httpSession) {
        String email = principal.getName();
        Student theStudent = studentService.findByEmail(email);
        Course theCourse = courseService.getCourse(theId);
        if ((theStudent != null) && theStudent.getCourses().size() == 3) {
            model.addAttribute("errorMessage", ErrorMessage.MAXIMUM_REGISTERED);
            model.addAttribute("goBackToProfile", ErrorMessage.GO_BACK_TO_PROFILE);
            return "error-page";
        }
        List<Student> students = theCourse.getStudents();
        for (Student studentsInCourse : students) {
            if (theStudent != null &&
                    studentsInCourse.getAccount().getEmail().equals(theStudent.getAccount().getEmail())) {
                model.addAttribute("errorMessage", " Sorry , " + email + "  "
                        + ErrorMessage.ALREADY_REGISTERED);
                model.addAttribute("goBack", ErrorMessage.GO_BACK_TO_COURSE);
                return "error-page";
            }
        }

        httpSession.setAttribute("email", email);
        if (theStudent != null) {
            courseService.addUserToCourse(theCourse,
                    studentService.findByEmail(email));
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
        Student theStudent = studentService.findByEmail(theEmail);
        List<Course> theCourses = courseService.getSelectedCourses(theId);
        if ((theCourses != null)){
            for (Course course : theCourses){
                    courseService.addUserToCourse(course,theStudent);
            }
            rd.addFlashAttribute("message", "courses were successfully added to  "
                    + (theStudent != null ? theStudent.getFirstName() : null));
            return "redirect:/admin";
        }
        
        rd.addFlashAttribute("courseAlreadyAdded","Duplicate courses found detected for  "
                +theStudent.getFirstName());
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
        List<Student> students = theCourse.getStudents();
        for (Student studentsInCourse : students) {
            if (studentsInCourse.getAccount().getEmail().equals(email)){
                httpSession.setAttribute("email", email);
                courseService.removeUserFromCourse(theCourse, studentService.findByEmail(email));
                model.addAttribute("userEmail", email);
                return "redirect:/students/details";
            }
        }
        model.addAttribute("errorMessage", email+"  "+ ErrorMessage.EMAIL_NOT_FOUND);
        model.addAttribute("goBackToProfile", ErrorMessage.GO_BACK_TO_PROFILE);
        return "error-page";
    }
}






