package com.softpager.cms.repositories;

import com.softpager.cms.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface CourseRepository extends JpaRepository<Course, Long> {


    List<Course> findByTitleLike(String theTitle);

    Set<Course> getCoursesByIdIn(long[] ids);
}
