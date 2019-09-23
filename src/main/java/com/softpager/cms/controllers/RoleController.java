package com.softpager.cms.controllers;

import com.softpager.cms.abstracts.AbstractUser;
import com.softpager.cms.entities.Role;
import com.softpager.cms.services.UserService;
import com.softpager.cms.services.RoleService;
import com.softpager.cms.utils.ErrorMessage;
import com.softpager.cms.utils.UserHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.*;

@Slf4j
@Controller
@RequestMapping("/admin/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserHelper userHelper;


    @GetMapping()
    public String getRoles(Model model) {
        List<Role> allRoles = roleService.getRoles();
        model.addAttribute("role", new Role());
        model.addAttribute("roles", allRoles);
        return "admin/user-role";
    }


    /*Here we use this method to create new role*/
    @PostMapping("/add-role")
    public String addRole(@Valid @ModelAttribute("role") Role theRole,
                          BindingResult br, RedirectAttributes rd) {
        if (br.hasErrors()){
            return "admin/user-role";
        }
        List<Role> allRole = roleService.getRoles();
        for (Role roles : allRole){
            if (roles.getName().equals(theRole.getName())){
                rd.addFlashAttribute("message","Role : " +theRole.getName()+ " " +
                            ""+ErrorMessage.ROLE_ALREADY_EXIST);
                return "redirect:/roles";
            }
        }
        roleService.save(theRole);
        rd.addFlashAttribute("message","Role : " +theRole.getName()+ "" +
                " "+ErrorMessage.ROLE_CREATED);
        return "redirect:/roles";
    }


    @GetMapping("/delete")
    public String deleteRole(@RequestParam("name") String name){
      Role theRole = roleService.findByName(name);
      List<AbstractUser> usersInRole = userService.findByRole(theRole);
      if (usersInRole != null){
          log.info("UUS {} :",usersInRole.size());
          for (AbstractUser user : usersInRole){
          }
       List<AbstractUser> usersInRole2 = userService.findByRole(theRole);
          log.info("UUS2 {} :",usersInRole2.size());
      }
      roleService.deleteByName(theRole.getName());

        return "redirect:/roles";
    }


    /*Here, we are using this method to get list of courses to  assign for a user
    (instructors or students*/

    @GetMapping("/get-roles")
    public String assignCourseToUserForm(@RequestParam("userEmail")String email,
                                         HttpSession httpSession){
        httpSession.setAttribute("email",email);
        return "redirect:/roles";
    }


    /*Here, we are using this method to assign roles to a
   user  using the id*/
    @RequestMapping("/assign-user-to-roles")
    public String assignCourse(@RequestParam("roleId") long theId,
                               RedirectAttributes rd, HttpSession httpSession) {
        String theEmail = (String) httpSession.getAttribute("email");
        AbstractUser theUser = userService.findByEmail(theEmail);
        Optional<Role> selectedRole = roleService.getRole(theId);
        if (selectedRole.isPresent()){
                roleService.addUserToRole(selectedRole.get(),theUser);
                       rd.addFlashAttribute("message", theUser.getFirstName()+
                    "has been assigned to "+ theUser.getRoles());
            return "redirect:/roles";
        }
        rd.addFlashAttribute("roleAlreadyExist"," Operation failed, Duplicate Role assignment for "
                +theUser.getFirstName());
        return "admin/user-role";
    }
}