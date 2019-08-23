package com.softpager.cms.services;

import com.softpager.cms.entities.Admin;

import com.softpager.cms.entities.Role;
import com.softpager.cms.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class AdminServiceImpl  implements AdminService{

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public List<Admin> getAdmins() {
        return adminRepository.findAll();
    }


    @Override
    public void saveAdmin(Admin theAdmin) {
        theAdmin.setPassword(passwordEncoder.encode(theAdmin.getPassword()));
        Role newRole = new Role("ADMIN");
        Set<Role> studentRoles = new HashSet<>();
        studentRoles.add(newRole);
        theAdmin.setRoles(studentRoles);
        adminRepository.save(theAdmin);
    }


    @Override
    public Optional<Admin> getAdmin(String email) {
        return adminRepository.findById(email);
    }

    @Override
    public void delete(String email) {
        adminRepository.deleteById(email);

    }
}
