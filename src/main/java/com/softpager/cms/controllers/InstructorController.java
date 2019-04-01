package com.softpager.cms.controllers;

import com.softpager.cms.entities.Instructor;
import com.softpager.cms.services.InstructorService;
import com.softpager.cms.services.UserPhotoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/instructors")
public class InstructorController {

    @Autowired
    private InstructorService instructorService;

    @Autowired
    private UserPhotoService userPhotoService;


    @GetMapping()
    public String getInstructors(Model model, @RequestParam(defaultValue = "0") int page){
        Page<Instructor> allInstructors = instructorService.getInstructors(PageRequest.of(page,5));
        log.info("The Instructors: {}", allInstructors);
        model.addAttribute("instructors", allInstructors);
        model.addAttribute("currentPage", page);
        return "instructor/instructors";
    }

    @GetMapping("/profile")
    public String getInstructor(@RequestParam("email") String email, Model model){
        Instructor theInstructor = instructorService.getInstructor(email);
        model.addAttribute("instructor", theInstructor);
        return "instructor/instructor-profile";
    }

    @GetMapping("/new")
    public String getForm(Model model){
        model.addAttribute("instructor", new Instructor());
        return "instructor/add-instructor";
    }

    @PostMapping("/create")
    public String createInstructor(@Valid @ModelAttribute("instructor") Instructor theInstructor){
        instructorService.createInstructor(theInstructor);
        return "redirect:/instructors";
    }




}
