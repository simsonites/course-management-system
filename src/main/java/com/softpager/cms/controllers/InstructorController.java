package com.softpager.cms.controllers;

import com.softpager.cms.abstracts.AbstractUser;
import com.softpager.cms.entities.Instructor;
import com.softpager.cms.entities.Student;
import com.softpager.cms.services.InstructorService;
import com.softpager.cms.services.FileUploadService;
import com.softpager.cms.services.UserService;
import com.softpager.cms.utils.CurrentUser;
import com.softpager.cms.utils.ErrorMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Slf4j
@Controller
@RequestMapping("/instructors")
public class InstructorController {

    @Autowired
    private InstructorService instructorService;

    @Autowired
    private FileUploadService fileUploadService;

    @Autowired
    private CurrentUser currentUser;

    @Autowired
    private UserService userService;


    @GetMapping()
    public String getInstructors(Model model, @RequestParam(defaultValue = "0") int page) {
        Page<Instructor> allInstructors = instructorService.getInstructors(PageRequest.of(page, 6));
        model.addAttribute("instructors", allInstructors);
        model.addAttribute("currentPage", page);
        return "instructor/instructors";
    }


    @GetMapping("/new")
    public String instructorForm(Model model){
        model.addAttribute("instructor", new Instructor());
        return "instructor/add-instructor";
    }

    @PostMapping("/create")
    public String createInstructor(@ModelAttribute("instructor") Instructor theInstructor){
        instructorService.createInstructor(theInstructor);
        return "redirect:/admin";
    }

    @GetMapping("/details")
    public String getInstructor(@RequestParam("userEmail") String email, Model model){
        AbstractUser theUser = userService.getUser(email);
        model.addAttribute("instructor", theUser);
        model.addAttribute("courses", theUser.getCourses());
        return "instructor/instructor-details";
    }


    // This method updates an existing instructor
    @GetMapping("/update")
    public String update(@RequestParam("email") String email, Model model) {
        Instructor theInstructor = instructorService.getInstructor(email);
        model.addAttribute("instructor", theInstructor);
        return "instructor/add-instructor";
    }


   //This method deletes  a instructor by the ID
    @GetMapping("/delete")
    public String delete(@RequestParam("userEmail") String email,
                         Principal principal, Model model) {
        if (currentUser.getCurrentUser(principal,email)){
            instructorService.delete(email);
            return "redirect:/students";
        }else {
            model.addAttribute("errorMessage", ErrorMessage.UNAUTHORIZED_OPERATION);
            model.addAttribute("goBack", ErrorMessage.GO_BACK);
        }
        return "error-page";
    }


}
