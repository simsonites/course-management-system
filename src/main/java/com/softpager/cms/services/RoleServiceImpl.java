package com.softpager.cms.services;


import com.softpager.cms.abstracts.CMSUser;
import com.softpager.cms.entities.Role;
import com.softpager.cms.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    @Override
    public void save(Role theRole) {
        roleRepository.save(theRole);

    }

    @Override
    public Optional<Role> getRole(long theId) {
        return roleRepository.findById(theId);
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

    public void deleteByName(String name) {
        roleRepository.deleteByName(name);
    }
}
