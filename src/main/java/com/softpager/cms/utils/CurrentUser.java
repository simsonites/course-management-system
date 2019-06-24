package com.softpager.cms.utils;

import com.softpager.cms.abstracts.AbstractUser;
import com.softpager.cms.services.UserService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


import java.security.Principal;
import java.util.Collection;

@Slf4j
@Data
@Component
public class CurrentUser {

  @Autowired
  private UserService userService;

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

}
