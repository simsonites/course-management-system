package com.softpager.cms.controllers;


import com.softpager.cms.abstracts.AbstractUser;
import com.softpager.cms.entities.Course;
import com.softpager.cms.entities.Role;
import com.softpager.cms.entities.Student;
import com.softpager.cms.exceptions.UserAlreadyExistException;
import com.softpager.cms.services.UserService;
import com.softpager.cms.services.CourseService;
import com.softpager.cms.services.StudentService;

import com.softpager.cms.utils.UserFeedbackMessage;
import com.softpager.cms.utils.UserHelper;
import com.softpager.cms.utils.ErrorMessage;
import com.softpager.cms.utils.ViewNames;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.*;


@Slf4j
@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courseService;

   @Autowired
    private UserHelper userHelper;

   private Course course = new Course();


    //Fetching all existing students from the database.
    @GetMapping
    public String getStudents(Model model, @RequestParam(defaultValue = "0") int page) {
        Page<Student> allStudents = studentService.getStudents(PageRequest.of(page, 8));
        model.addAttribute("students", allStudents);
        model.addAttribute("currentPage", page);
        return ViewNames.STUDENTS;
    }

    //This method shows the form to create new student
    @GetMapping("/new")
    public String showForm(Model model) {
        AbstractUser student = new Student();
        model.addAttribute("student", student);
        return ViewNames.ADD_STUDENT;
    }

    // This method create a new student
    @PostMapping("/create")
    public String createStudent(@Valid @ModelAttribute("student") Student theStudent,
                                BindingResult br, RedirectAttributes rd, Model model) {
        if (br.hasErrors()){
            return ViewNames.ADD_STUDENT;
        }
        AbstractUser student = userService.findByEmail(theStudent.getEmail());
        if (student != null){
            model.addAttribute("exists",theStudent.getEmail() + "  already exist");
            return ViewNames.ADD_STUDENT;
        }
        studentService.saveStudent(theStudent);
        rd.addFlashAttribute("success", UserFeedbackMessage.SUCCESS);
        return "redirect:/students";
    }

    /*This method helps us to view a student details base on the email*/
    @GetMapping("/details")
    public String getStudent(@RequestParam("userEmail") String email, Model model){
        AbstractUser theUser = userService.findByEmail(email);
        if (theUser ==null){
            return "redirect:/login";
        }
        Set<Role> userRoles = theUser.getRoles();
        Set<String> roleName = new HashSet<>();
        if (userRoles !=null){
            for (Role r : userRoles){
                roleName.add(r.getName());
            }
        }
        model.addAttribute("student", theUser);
        model.addAttribute("role",roleName);
        model.addAttribute("courses", theUser.getCourses());
        return ViewNames.STUDENT_DETAILS;
    }

    // This method updates an existing student
    @GetMapping("/update")
    public String update(@RequestParam("studentId") long theId, Model model) {
        Optional<Student> theStudent = studentService.getStudent(theId);
        if (theStudent.isPresent()) {
            Student student = theStudent.get();
            model.addAttribute("student", student);
            return "student/update-student";
        }
        throw new UsernameNotFoundException("Sorry, this user is  not found");
    }

     /*Saving the updated user (student)*/
    @PostMapping("/save-update")
    public String saveStudentUpdate(@ModelAttribute("student")Student theStudent){
        Optional<Student> student = studentService.getStudent(theStudent.getId());
          if (student != null) {
            Student st = student.get();
            st.setFirstName(theStudent.getFirstName());
            st.setLastName(theStudent.getLastName());
            st.setEmail(theStudent.getEmail());
            st.setGender(theStudent.getGender());
           studentService.saveStudent(st);
            return "redirect:/students";
        }
        throw new UsernameNotFoundException("Student not found !");
    }

    //This method deletes  a student by the email
    @GetMapping("/delete")
    public String deleteStudent(@RequestParam("userEmail") String email,
                         Principal principal, Model model) {
   if (!userHelper.getCurrentUser(principal, email)) {
       model.addAttribute("errorMessage", ErrorMessage.UNAUTHORIZED_OPERATION);
       model.addAttribute("goBack", ErrorMessage.GO_BACK);
       return "error-page";
        }
   else {
        userService.breakUserRelationship(email);
        userService.deleteUser(email);
         return "redirect:/students";
        }
    }

    @GetMapping("/admin/manage-students")
    public String getAllStudents(Model model, @RequestParam(defaultValue="") String email){
        model.addAttribute("students",studentService.findByEmail(email));
        return "admin/manage-students";
    }

}