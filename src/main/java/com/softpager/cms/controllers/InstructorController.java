package com.softpager.cms.controllers;

import com.softpager.cms.entities.Instructor;
import com.softpager.cms.services.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/instructors")
public class InstructorController {

    private final InstructorService instructorService;

    @Autowired
    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping("")
    public String getInstructors(Model model, @RequestParam(defaultValue ="0") int page){
       Page<Instructor> allInstructors = instructorService.getInstructors(PageRequest.of(page,5));
       model.addAttribute("instructors", allInstructors);
       return "instructor/instructors";
    }

    @GetMapping("/instructor")
    public String getInstructor(@RequestParam long theId, Model model){
        Instructor theInstructor = instructorService.getInstructor(theId);
        model.addAttribute("info", theInstructor);
        return "instructor/instructor-profile";
    }

    @GetMapping("/new")
    public String showForm(Model model){
        model.addAttribute("instructor", new Instructor());
        return "instructor/add-instructor";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("instructor") Instructor theInstructor){
        instructorService.create(theInstructor);
        return "redirect:/instructors";
    }
}
