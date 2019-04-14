package com.softpager.cms.repositories;

import com.softpager.cms.abstracts.User;
import com.softpager.cms.entities.Course;
import com.softpager.cms.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository  extends JpaRepository<Course, Long> {

    List<Course> findByInstructor(String email);

    List<User> findByStudents(String email);

}
