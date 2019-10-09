package com.softpager.cms.repositories;

import com.softpager.cms.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
    void deleteByName(String name);


}
