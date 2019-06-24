package com.softpager.cms.services;

import com.softpager.cms.abstracts.AbstractUser;
import com.softpager.cms.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public AbstractUser getUser(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void save(AbstractUser theUser) {
        userRepository.save(theUser);

    }

    @Override
    public void delete(String email) {
        userRepository.deleteById(email);
    }
}
