package com.softpager.cms.repositories;

import com.softpager.cms.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;


public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String student);

    List<Role> getRolesByIdIn(long[] theIds);

}
