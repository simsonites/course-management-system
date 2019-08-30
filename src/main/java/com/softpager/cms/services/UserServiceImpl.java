package com.softpager.cms.services;

import com.softpager.cms.abstracts.AbstractUser;
import com.softpager.cms.entities.Course;
import com.softpager.cms.entities.Role;
import com.softpager.cms.repositories.UserRepository;
import com.softpager.cms.security.CustomUserSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {


    private Course course = new Course();


    @Autowired
    private UserRepository userRepository;


    public Optional<AbstractUser> getUser(long theId) {
        return userRepository.findById(theId);
    }

    @Override
    public void save(AbstractUser theUser) {
        userRepository.save(theUser);
    }

    @Override
    public void deleteByEmail(String email) {
        course.removeUserFromCourse(userRepository.findByEmail(email));
        userRepository.deleteByEmail(email);
    }

    @Override
    public AbstractUser findByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    @Override
    public List findByRole(Role theRole) {
        return userRepository.findByRoles(theRole);
    }


    @Override
    public UserDetails loadUserByUsername(String username)throws UsernameNotFoundException {
        AbstractUser theUser = userRepository.findByEmail(username);
        if (theUser == null) {
            throw new UsernameNotFoundException("Sorry, No user found!");
        }
        return new CustomUserSecurity(theUser);
    }
}
