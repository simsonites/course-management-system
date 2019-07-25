package com.softpager.cms.services;

import com.softpager.cms.abstracts.CMSUser;
import com.softpager.cms.entities.Role;

import java.util.List;


public interface RoleService {
    List<Role> getRoles();

    void addRole(Role theRole);

    Role getRole(long theId);

    Role findByName(String student);

    void deleteRole(long theId);

    void addUserToRole(Role role, CMSUser theUser);

    List<Role> getSelectedRoles(long[] theId);
}
