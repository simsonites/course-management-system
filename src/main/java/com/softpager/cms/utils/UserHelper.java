package com.softpager.cms.utils;

import com.softpager.cms.abstracts.AbstractUser;
import com.softpager.cms.entities.Course;
import com.softpager.cms.services.CourseService;
import com.softpager.cms.services.UserService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


import java.security.Principal;
import java.util.List;

@Slf4j
@Data
@Component
public class UserHelper {

  @Autowired
  private UserService userService;

  @Autowired
    CourseService courseService;

    public boolean getCurrentUser(Principal principal, String theEmail){
        String currentUserEmail = principal.getName();
        AbstractUser theUser = userService.getUser(currentUserEmail);
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

    /*This method deletes a user from the database by the Id (email)*/
    public void deleteUser(String email){
        List<Course> userCourses = userService.getUser(email).getCourses();
        if (userCourses != null){
            for (Course course : userCourses){
                courseService.removeUserFromCourse(course,userService.getUser(email));
            }
        }
        userService.deleteUser(email);
    }

}
