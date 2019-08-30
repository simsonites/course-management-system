package com.softpager.cms.services;

import com.softpager.cms.abstracts.AbstractUser;
import com.softpager.cms.entities.Role;

import java.util.List;
import java.util.Optional;


public interface RoleService {
    List<Role> getRoles();

    void save(Role theRole);

  Optional<Role> getRole(long theId);

    Role findByName(String student);

    void deleteRole(long theId);

    void deleteByName(String name);

   // void addUserToRole(Role role, AbstractUser theUser);

    List<Role> getSelectedRoles(long[] theId);

    void addUserToRole(Role role, AbstractUser theUser);
}
