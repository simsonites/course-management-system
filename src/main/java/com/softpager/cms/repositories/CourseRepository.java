package com.softpager.cms.repositories;

import com.softpager.cms.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository  extends JpaRepository<Course, Long> {
}
