package com.softpager.cms.controllers;

import com.softpager.cms.abstracts.CMSUser;
import com.softpager.cms.entities.Course;
import com.softpager.cms.entities.Role;
import com.softpager.cms.services.CMSUserService;
import com.softpager.cms.services.RoleService;
import com.softpager.cms.utils.UserHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Controller
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private CMSUserService cmsUserService;

    @Autowired
    private UserHelper userHelper;

    @GetMapping()
    public String getRoles(Model model) {
        List<Role> allRoles = roleService.getRoles();
        model.addAttribute("role", new Role());
        model.addAttribute("roles", allRoles);
        return "admin/user-role";
    }

    @PostMapping("/add-role")
    public String addRole(@ModelAttribute("role") Role theRole) {
        roleService.save(theRole);
        return "redirect:/roles";
    }

    @GetMapping("/delete")
    public String deleteRole(long theId){
        roleService.deleteRole(theId);
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
    @RequestMapping("/assign-user-to-role")
    public String assignCourse(@RequestParam("roleId") long theId,
                               RedirectAttributes rd, HttpSession httpSession) {
        String theEmail = (String) httpSession.getAttribute("email");
        Optional<Role> selectedRole = roleService.getRole(theId);
        CMSUser theUser = cmsUserService.findByEmail(theEmail);
        if ((theUser != null) && (selectedRole.isPresent())){
           theUser.setRoles(selectedRole.get());
            cmsUserService.save(theUser);
            rd.addFlashAttribute("message", theUser.getFirstName()+
                    "has been assigned to "+ theUser.getRoles().getName()+ "  Role");

        }
        return "redirect:/roles";
    }

}