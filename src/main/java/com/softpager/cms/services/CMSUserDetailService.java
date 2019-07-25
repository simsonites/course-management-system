package com.softpager.cms.services;

import com.softpager.cms.abstracts.CMSUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


import java.util.Arrays;
import java.util.Collection;

@Slf4j
@Component
public class CMSUserDetailService implements UserDetailsService {

    @Autowired
    private CMSUserService cmsUserService;

    @Autowired
    private PasswordEncoder passwordEncoder;



    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        final CMSUser theUser = cmsUserService.findByEmail(email);
        log.info("RRR {} : ", theUser.getEmail());
        log.info("RRR {} : ", theUser.getRoles());
        return  new User(theUser.getEmail(),passwordEncoder.encode(theUser.getPassword()),
                true, true, true, true, getAuthorities(String.valueOf(theUser.getRoles())));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(String role) {
        return Arrays.asList(new SimpleGrantedAuthority(role));
    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
