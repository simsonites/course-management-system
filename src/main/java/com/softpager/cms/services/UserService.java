package com.softpager.cms.services;

import com.softpager.cms.abstracts.AbstractUser;
import com.softpager.cms.entities.Role;

import java.util.List;
import java.util.Optional;


public interface UserService {

    Optional<AbstractUser> getUser(long theId);

    void save(AbstractUser theUser);

    void deleteByEmail(String email);

    AbstractUser findByEmail(String email);

    List findByRole(Role theRole);

}
