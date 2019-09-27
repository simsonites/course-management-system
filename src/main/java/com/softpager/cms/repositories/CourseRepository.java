package com.softpager.cms.repositories;

import com.softpager.cms.entities.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface CourseRepository extends JpaRepository<Course, Long> {


    Page<Course> findByTitleLike(String theTitle, Pageable page);

    List<Course> getCoursesByIdIn(long[] ids);

    List<Course> findByTitleLike(String s);
}
