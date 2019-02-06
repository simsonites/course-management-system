package com.softpager.cms.services;

import com.softpager.cms.entities.Instructor;
import com.softpager.cms.repositories.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class instructorServiceImpl implements InstructorService{

    private  InstructorRepository instructorRepository;


    @Autowired
    public instructorServiceImpl(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    @Override
    public Page<Instructor> getInstructors(PageRequest pageRequest) {
        return this.instructorRepository.findAll(pageRequest) ;
    }

    @Override
    public Instructor getInstructor(long theId) {
        Optional<Instructor> theInstructor = instructorRepository.findById(theId);
        return theInstructor.orElse(null);
    }

    @Override
    public void create(Instructor instructor) {
        instructorRepository.save(instructor);
    }

}
