package com.softpager.cms.controllers;

import com.softpager.cms.abstracts.AbstractUser;
import com.softpager.cms.entities.Course;
import com.softpager.cms.repositories.CourseRepository;
import com.softpager.cms.services.CourseService;
import com.softpager.cms.services.UserService;
import com.softpager.cms.utils.ErrorMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@Controller
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private UserService userService;

    private CourseRepository courseRepo;


    @GetMapping("/course")
    public String getCourse(@RequestParam("courseId") long theId, Model model) {
        Course theCourse = courseService.getCourse(theId);
        model.addAttribute("course", theCourse);
        model.addAttribute("courses", this.getAllCourses());
        return "course/course-detail";
    }

    @RequestMapping("/register-for-course")
    public String registerForCourse(@RequestParam("courseId") long theId, Principal principal, Model model,
                                                                        HttpSession httpSession) {
        String email = principal.getName();
        AbstractUser theUser = userService.findByEmail(email);
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
            courseService.addUserToCourse(theCourse,theUser);
        }
        model.addAttribute("userEmail", email);
        return "redirect:/user/profile";
    }


   @RequestMapping("/remove-user-course")
    public String removeUserFromCourse(@RequestParam("courseId") long theId , Model model,
                                       Principal principal) {
        String email = principal.getName();
         model.addAttribute("userEmail", email);
         Course theCourse = courseService.getCourse(theId);
       courseService.removeUserFromCourse(theCourse, userService.findByEmail(email));
        for (AbstractUser user : theCourse.getUsers()) {
                if (user.getEmail().equals(email))
                courseService.removeUserFromCourse(theCourse,user);
            }
         return "redirect:/user/profile";
    }


    private List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    /*********THIS PART IS ONLY AVAILABLE TO ADMIN USERS*********/
    /**Here, we are using this method to assign multiple courses to a  user  using the email**/

    @RequestMapping("/admin/assign-multiple-courses")
    public String assignCourse(@RequestParam("courseId") long[] theId, RedirectAttributes rd, HttpSession httpSession) {
        String theEmail = (String) httpSession.getAttribute("email");
        AbstractUser theUser = userService.findByEmail(theEmail);
        List<Course> theCourses = courseService.getSelectedCourses(theId);
        List<Course> coursesToAdd = new ArrayList<>();
        if ((theCourses != null)){
            coursesToAdd.addAll(theCourses);
            courseService.saveAll(coursesToAdd,theUser);
            rd.addFlashAttribute("message", "courses were successfully added to  "
                    + (theUser != null ? theUser.getFirstName() : null));
            assert theUser != null;
            rd.addFlashAttribute("email", theUser.getEmail());
            return "redirect:/admin";
        }
        rd.addFlashAttribute("courseAlreadyAdded","Duplicate courses found detected for  "
                +theUser.getFirstName());
        return "admin/manage-courses";
    }


    //This method show the form to create new courses
    @GetMapping("/admin/course-form")
    public String showForm(Model model) {
        Course course = new Course();
        model.addAttribute("course", course);
        return "course/add-course";
    }

    /****This method actually saves the course to the database****/

    @PostMapping("/admin/create-course")
    public String saveCourse(@Valid @ModelAttribute Course theCourse,
                             BindingResult br, Model model) {
        if (br.hasErrors()){
            return "course/add-course";
        }
        if (courseExists(theCourse.getTitle())){
            model.addAttribute("courseExists",  theCourse.getTitle()+ "  already exist");
            return "course/add-course";
        }
        courseService.saveCourse(theCourse);
        return "redirect:/courses/admin/manage-courses";
    }

    private boolean courseExists(String cTitle){
        Course theCourse = courseService.findCourseByTitle(cTitle);
        if (theCourse != null){
            return true;
        }
        return false;
    }


    //This method deletes a course from the database by the ID
    @GetMapping("/admin/delete-course")
    public String deleteCourse(@RequestParam("courseId") long theId) {
        List<AbstractUser> coursesUsers = courseService.getCourse(theId).getUsers();
        if (coursesUsers != null){
            for (AbstractUser user : coursesUsers){
                courseService.removeUserFromCourse(courseService.getCourse(theId),user);
            }
        }
        courseService.deleteCourse(theId);
        return "redirect:/admin/manage-courses";
    }

    //This method updates a course in the database by the ID
    @GetMapping("/admin/update-course")
    public String updateCourse(@RequestParam("courseId") long theId, Model model) {
        Course theCourse = courseService.getCourse(theId);
        model.addAttribute("course", theCourse);
        return "course/add-course";
    }

    /*Here, we are using this method to get list of courses to  assign for a user */
    @GetMapping("/admin/get-courses")
    public String assignCourseToUserForm(@RequestParam("userEmail")String email, HttpSession httpSession){
        if (email == null){
            return "redirect:/login";
        }
        httpSession.setAttribute("email",email);
        return "redirect:/courses/admin/manage-courses";
    }

    /* This method is used to perform search operation on courses in the admin panel*/
    @GetMapping("/admin/manage-courses")
    public String manageCourses(Model model, @RequestParam(defaultValue="") String title) {
        model.addAttribute("courses", courseService.manageCourse(title));
        return "admin/manage-courses";
    }


}






