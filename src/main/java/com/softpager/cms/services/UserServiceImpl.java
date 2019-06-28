package com.softpager.cms.services;

import com.softpager.cms.abstracts.AbstractUser;
import com.softpager.cms.entities.Course;
import com.softpager.cms.repositories.CourseRepository;
import com.softpager.cms.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {


    private Course course = new Course();


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public AbstractUser getUser(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void save(AbstractUser theUser) {
        userRepository.save(theUser);

    }

    @Override
    public void deleteUser(String email) {
        course.removeUserFromCourse(userRepository.findByEmail(email));
        userRepository.deleteById(email);
    }
}
