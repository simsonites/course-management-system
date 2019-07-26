package com.softpager.cms.services;

import com.softpager.cms.entities.Role;

import java.util.List;
import java.util.Optional;


public interface RoleService {
    List<Role> getRoles();

    void save(Role theRole);

  Optional<Role> getRole(long theId);

    Role findByName(String student);

    void deleteRole(long theId);
}
