package com.softpager.cms.services;

import com.softpager.cms.entities.Instructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface InstructorService {
    Page<Instructor> getInstructors(PageRequest of);

    void createInstructor(Instructor theInstructor);

    Instructor getInstructor(String email);

    void delete(String email);
}
