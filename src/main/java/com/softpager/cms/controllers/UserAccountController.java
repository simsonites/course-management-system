package com.softpager.cms.controllers;

import com.softpager.cms.abstracts.CMSUser;
import com.softpager.cms.entities.Student;
import com.softpager.cms.entities.UserAccount;
import com.softpager.cms.services.CMSUserService;
import com.softpager.cms.services.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/account")
public class UserAccountController {


    @Autowired
    private UserAccountService accountService;

    @Autowired
    private CMSUserService userService;


    @GetMapping
    public String getAccountForm(Model model){
        model.addAttribute("student",new UserAccount());
        return "account/registration";
    }


    @PostMapping("/create-account")
    public String register(@Valid @ModelAttribute("student") UserAccount account, HttpSession session){
            UserAccount theUser =  accountService.saveAccount(account);
        session.setAttribute("email",theUser.getEmail());
        return "redirect:/account/complete-registration";
    }


    @RequestMapping("/complete-registration")
    public String getRegistrationForm(Model model, RedirectAttributes rd){
        model.addAttribute("student", new Student());
        return "account/user-form";
    }



    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }
}
