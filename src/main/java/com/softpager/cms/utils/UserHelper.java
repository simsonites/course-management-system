package com.softpager.cms.utils;

import com.softpager.cms.abstracts.AbstractUser;
import com.softpager.cms.entities.Course;
import com.softpager.cms.entities.Role;
import com.softpager.cms.services.CourseService;
import com.softpager.cms.services.UserService;
import com.softpager.cms.services.RoleService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.*;

@Slf4j
@Data
@Component
public class UserHelper {

    @Autowired
    private UserService userService;

    @Autowired
    CourseService courseService;
    @Autowired
    private RoleService roleService;

    private Course course = new Course();
    private Role role = new Role();

    public boolean getCurrentUser(Principal principal, String theEmail) {
        String currentUserEmail = principal.getName();
        AbstractUser theUser = userService.findByEmail(currentUserEmail);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean hasAdminRole = false;
        for (GrantedAuthority role : authentication.getAuthorities()) {
            if (role.getAuthority().equals("ROLE_ADMIN")) {
                hasAdminRole = true;
                break;
            }
        }
        return (hasAdminRole) || (theUser.getEmail().equals(theEmail));
    }

 }
