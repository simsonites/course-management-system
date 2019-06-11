package com.softpager.cms.controllers;

import com.softpager.cms.entities.Admin;
import com.softpager.cms.entities.Course;
import com.softpager.cms.entities.Student;
import com.softpager.cms.services.AdminService;
import com.softpager.cms.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private CourseService courseService;


    //This method will get all courses from the database.
    @GetMapping()
    public String adminHome(Model model, @RequestParam(defaultValue = "0") int page) {
        Page<Course> allCourses = courseService.getCourses(PageRequest.of(page, 5));
        List<Admin> allAdmin = adminService.getAdmins();
        int adminCount = allAdmin.size();
        model.addAttribute("courses", allCourses);
        model.addAttribute("currentPage", page);
        model.addAttribute("admins", adminCount);
        return "/admin/admin-home";
    }

    //Fetching all existing students from the database.
    @GetMapping("/all")
    public String getAdmins(Model model) {
        List<Admin> allAdmin = adminService.getAdmins();
        model.addAttribute("admin", allAdmin);
        return "/admin/all-admin";
    }

    //This method shows the form to create new student
    @GetMapping("/new")
    public String adminForm(Model model) {
        model.addAttribute("admin", new Admin());
        return "/admin/add-admin";
    }


    // This method create a new student
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

    //This method deletes  a student by the ID
    @GetMapping("/delete")
    public String delete(@RequestParam("userEmail") String email) {
        adminService.delete(email);
        return "redirect:/admins";
    }



    /*
    ******************************************************************
     * ******************************************************************
     * ********************************************************************
     */

    /*START ADMIN COURSE MANAGEMENT*/


    //This method will get all courses from the database.
    @GetMapping("/courses")
    public String getCourses(Model model, @RequestParam(defaultValue = "0") int page) {
        Page<Course> allCourses = courseService.getCourses(PageRequest.of(page, 5));
        model.addAttribute("courses", allCourses);
        model.addAttribute("currentPage", page);
        return "admin/admin-home";
    }


    //This method show the form to create new courses
    @GetMapping("/course-form")
    public String showForm(Model model) {
        Course course = new Course();
        model.addAttribute("course", course);
        return "course/add-course";
    }

    //This method actually saves the course to the database
    @PostMapping("/create-course")
    public String saveCourse(@ModelAttribute Course theCourse) {
        courseService.saveCourse(theCourse);
        return "redirect:/admin/courses";
    }


    //This method deletes a course from the database by the ID
    @GetMapping("/delete-course")
    public String deleteCourse(@RequestParam("courseId") long theId) {
        courseService.deleteCourse(theId);
        return "redirect:/admins/courses";
    }


    @GetMapping("/update-course")
    public String updateCourse(@RequestParam("courseId") long theId, Model model) {
        Course theCourse = courseService.getCourse(theId);
        model.addAttribute("course", theCourse);
        return "course/add-course";
    }


    /*
    ******************************************************************
     * ******************************************************************
     * ********************************************************************
     * /

    /*END ADMIN COURSE MANAGEMENT*/



}
