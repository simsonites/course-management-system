package com.softpager.cms.utils;

import com.softpager.cms.abstracts.CMSUser;
import com.softpager.cms.entities.Course;
import com.softpager.cms.entities.Role;
import com.softpager.cms.entities.Student;
import com.softpager.cms.entities.UserAccount;
import com.softpager.cms.services.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;
import java.util.Set;

@Slf4j
@Data
@Component
public class UserHelper {

  @Autowired
  private CMSUserService cmsUserService;

  @Autowired
    CourseService courseService;
  @Autowired
  private RoleService roleService;

  @Autowired
  private StudentService studentService;

  @Autowired
  private UserAccountService accountService;

    public boolean getCurrentUser(Principal principal, String theEmail){
        String currentUserEmail = principal.getName();
        UserAccount account = getUser(currentUserEmail);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean hasAdminRole = false;
        for (GrantedAuthority role : authentication.getAuthorities()) {
            if (role.getAuthority().equals("ROLE_ADMIN")) {
                hasAdminRole = true;
                break;
            }
        }
        return (hasAdminRole) || (account.getEmail().equals(theEmail));
    }

      //This method deletes a user from the database by the Id (email)
    public void deleteUser(String email){
        Set<Course> userCourses = studentService.findByEmail(email).getCourses();
        Role userRole = cmsUserService.findByEmail(email).getRole();
        if (userCourses != null){
            for (Course course : userCourses){
                courseService.removeUserFromCourse(course, studentService.findByEmail(email));
            }
        }
        studentService.deleteByEmail(email);
    }

    /* Let's user this method to get any available user for the session*/
   private UserAccount getUser(String email){
       return accountService.findByEmail(email);
   }

}
