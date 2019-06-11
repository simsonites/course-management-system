package com.softpager.cms.services;

import com.softpager.cms.entities.Instructor;
import com.softpager.cms.entities.Role;
import com.softpager.cms.repositories.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class InstructorServiceImpl implements InstructorService {

    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public Page<Instructor> getInstructors(PageRequest pages) {
        return instructorRepository.findAll(pages);
    }

    @Override
    public Instructor getInstructor(String email) {
        Optional<Instructor> theInstructor = instructorRepository.findById(email);
        return theInstructor.orElse(null);
    }

    @Override
    public void createInstructor(Instructor theInstructor) {
        theInstructor.setPassword(passwordEncoder.encode(theInstructor.getPassword()));
        Role newRole = new Role("INSTRUCTOR");
        Set<Role> instructorRoles = new HashSet<>();
        instructorRoles.add(newRole);
        theInstructor.setRoles(instructorRoles);
        instructorRepository.save(theInstructor);
    }

}
