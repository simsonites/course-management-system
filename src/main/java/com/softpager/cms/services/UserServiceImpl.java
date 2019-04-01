package com.softpager.cms.services;

import com.softpager.cms.abstracts.User;
import com.softpager.cms.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUser(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void save(User theUser) {
        userRepository.save(theUser);

    }
}
