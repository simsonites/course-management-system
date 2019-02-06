package com.softpager.cms.services;

import com.softpager.cms.entities.Instructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface InstructorService {
    Page<Instructor> getInstructors(PageRequest of);

    Instructor getInstructor(long theId);
    void create(Instructor instructor);
}
