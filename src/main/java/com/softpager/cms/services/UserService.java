package com.softpager.cms.services;

import com.softpager.cms.abstracts.AbstractUser;
import com.softpager.cms.entities.Course;
import com.softpager.cms.entities.Role;

import java.util.List;
import java.util.Optional;
import java.util.Set;


public interface UserService {

    Optional<AbstractUser> getUser(long theId);

    void save(AbstractUser theUser);

    void breakUserRelationship(String email);

    void deleteUser(String email);

    AbstractUser findByEmail(String email);

    List findByRole(Role theRole);

    Optional<AbstractUser> findById(long userId);

}
