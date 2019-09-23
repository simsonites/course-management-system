package com.softpager.cms.repositories;

import com.softpager.cms.abstracts.AbstractUser;
import com.softpager.cms.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<AbstractUser, Long> {

    AbstractUser findByEmail(String email);
    void deleteByEmail(String email);

    List findByRoles(Role theRole);
}
