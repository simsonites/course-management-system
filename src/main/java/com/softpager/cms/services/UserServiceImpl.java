package com.softpager.cms.services;

import com.softpager.cms.abstracts.CMSUser;
import com.softpager.cms.entities.Course;
import com.softpager.cms.entities.Role;
import com.softpager.cms.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class UserServiceImpl implements UserService {


    private Course course = new Course();


    @Autowired
    private UserRepository userRepository;


    public Optional<CMSUser> getUser(long theId) {
        return userRepository.findById(theId);
    }

    @Override
    public void save(CMSUser theUser) {
        userRepository.save(theUser);
    }

    @Override
    public void deleteByEmail(String email) {
        course.removeUserFromCourse(userRepository.findByEmail(email));
        userRepository.deleteByEmail(email);
    }

    @Override
    public CMSUser findByEmail(String email) {
        return  userRepository.findByEmail(email);
    }


    @Override
    public List findByRoles(Role theRole) {
        return userRepository.findByRoles(theRole);
    }

}
