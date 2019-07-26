package com.softpager.cms.repositories;

import com.softpager.cms.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;



public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String student);

}
