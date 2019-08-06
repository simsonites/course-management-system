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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Slf4j
@Service
public class CMSUserDetailService implements UserDetailsService {

    @Autowired
    private CMSUserService cmsUserService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;



    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        final CMSUser theUser = cmsUserService.findByEmail(email);
      //  log.info("RRR {} : ", theUser.getEmail());
        log.info("RRR {} : ", theUser.getRole().getName());
        GrantedAuthority authority = new SimpleGrantedAuthority(theUser.getRole().getName());
       // return  new User(theUser.getEmail(),passwordEncoder.encode(theUser.getPassword()), Collections.singleton(authority));
        return null;
    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
