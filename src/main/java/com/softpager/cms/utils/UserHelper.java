package com.softpager.cms.utils;

import com.softpager.cms.abstracts.CMSUser;
import com.softpager.cms.entities.Course;
import com.softpager.cms.services.CourseService;
import com.softpager.cms.services.CMSUserService;
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

    public boolean getCurrentUser(Principal principal, String theEmail){
        String currentUserEmail = principal.getName();
        CMSUser theUser = cmsUserService.findByEmail(currentUserEmail);
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
        Set<Course> userCourses = cmsUserService.findByEmail(email).getCourses();
        if (userCourses != null){
            for (Course course : userCourses){
                courseService.removeUserFromCourse(course, cmsUserService.findByEmail(email));
            }
        }
        cmsUserService.deleteByEmail(email);
    }


    /* Let's user this method to get any available user for the session*/
    public CMSUser getUser(HttpSession session)throws UsernameNotFoundException {
         String email = (String) session.getAttribute("email");
        if (email != null){
            return cmsUserService.findByEmail(email);
        }
       throw new UsernameNotFoundException(email + " is not associated with any user!");
    }

}
