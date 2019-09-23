package com.softpager.cms.controllers;

import com.softpager.cms.abstracts.AbstractUser;
import com.softpager.cms.entities.Admin;
import com.softpager.cms.entities.Course;
import com.softpager.cms.services.AdminService;
import com.softpager.cms.services.CourseService;
import com.softpager.cms.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private StudentService studentService;


    //This method will get all courses from the database.
    @GetMapping()
    public String adminHome(Model model) {
        List<Admin> allAdmin = adminService.getAdmins();
        int adminCount = allAdmin.size();
        model.addAttribute("admins", adminCount);
        return "admin/admin-home";
    }

    //Fetching all existing students from the database.
    @GetMapping("/all")
    public String getAdmins(Model model) {
        List<Admin> allAdmin = adminService.getAdmins();
        model.addAttribute("admin", allAdmin);
        return "admin/all-admin";
    }

    //This method shows the form to create new student
    @GetMapping("/new")
    public String adminForm(Model model) {
        model.addAttribute("admin", new Admin());
        return "admin/add-admin";
    }


    // This method create a new admin
    @PostMapping("/create")
    public String createAdmin(@ModelAttribute("admin") Admin theAdmin) {
        adminService.saveAdmin(theAdmin);
        return "redirect:/admin";
    }

    // This method updates an existing student
    @GetMapping("/update")
    public String update(@RequestParam("userEmail") String email, Model model) {
        Optional<Admin> theAdmin = adminService.getAdmin(email);
        model.addAttribute("admin", theAdmin);
        return "admin/add-admin";
    }
    //This method deletes  a admin by the ID
    @GetMapping("/delete")
    public String delete(@RequestParam("userEmail") String email) {
        adminService.delete(email);
        return "redirect:/admin";
    }

}
