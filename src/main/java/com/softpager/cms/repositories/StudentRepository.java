package com.softpager.cms.repositories;

import com.softpager.cms.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository  extends JpaRepository<Student, Long> {
}
