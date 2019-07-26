package com.softpager.cms.services;

import com.softpager.cms.abstracts.CMSUser;
import com.softpager.cms.entities.Instructor;
import com.softpager.cms.entities.Role;
import com.softpager.cms.repositories.CMSUserRepository;
import com.softpager.cms.repositories.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class InstructorServiceImpl implements InstructorService {

    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private RoleService roleService;


    @Override
    public Page<Instructor> getInstructors(PageRequest pages) {
        return instructorRepository.findAll(pages);
    }


    @Override
    public void createInstructor(Instructor theInstructor) {
        theInstructor.setPassword(passwordEncoder.encode(theInstructor.getPassword()));
        Role newRole = roleService.findByName("INSTRUCTOR");
        theInstructor.setRoles(newRole);
        instructorRepository.save(theInstructor);
    }

    @Override
    public List<Instructor> getAllCourses() {
        return instructorRepository.findAll();
    }
}
