package com.softpager.cms.utils;

import com.softpager.cms.entities.Course;
import com.softpager.cms.entities.Student;
import com.softpager.cms.repositories.CourseRepository;
import com.softpager.cms.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;


@Component
public class SeedData {

    private String description = "Lorem Ipsum is simply dummy text of the printing " +
            "and typesetting industry. Lorem Ipsum has been the " +
            "industry's standard dummy text ever since the 1500s," +
            " when an unknown printer took a galley of type and " +
            "scrambled it to make a type ";


}
