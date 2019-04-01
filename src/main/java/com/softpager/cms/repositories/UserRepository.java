package com.softpager.cms.repositories;

import com.softpager.cms.abstracts.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findByEmail(String email);
}
