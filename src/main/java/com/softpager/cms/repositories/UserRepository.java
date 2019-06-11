package com.softpager.cms.repositories;

import com.softpager.cms.abstracts.AbstractUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AbstractUser, String> {
    AbstractUser findByEmail(String email);
}
