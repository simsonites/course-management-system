package com.softpager.cms.controllers;


import com.softpager.cms.services.AdminService;
import com.softpager.cms.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private CourseService courseService;

 private AdminService adminService;

 @RequestMapping()
 public String getAdmins(Model model){
     adminService.getAdmins();
     return "admins";
 }

}
