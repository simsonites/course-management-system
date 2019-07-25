package com.softpager.cms.services;

import com.softpager.cms.abstracts.CMSUser;
import com.softpager.cms.entities.Role;
import com.softpager.cms.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> getRoles() {
       return roleRepository.findAll();
    }

    @Override
    public void addRole(Role theRole) {
        roleRepository.save(theRole);

    }

    @Override
    public Role getRole(long theId) {
        return roleRepository.getOne(theId);
    }

    @Override
    public Role findByName(String student) {
        return roleRepository.findByName(student);
    }

    @Override
    public void deleteRole(long theId) {
        roleRepository.deleteById(theId);
    }

    @Override
    public void addUserToRole(Role role, CMSUser theUser) {
        role.addRoleToUSer(theUser);
        roleRepository.save(role);
    }

    @Override
    public List<Role> getSelectedRoles(long[] theId) {
        return roleRepository.getRolesByIdIn(theId);
    }
}
