package com.softpager.cms.entities;

import javax.persistence.Entity;
import javax.persistence.Table;


import com.softpager.cms.abstracts.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="students")
@SuppressWarnings("serial")
@EqualsAndHashCode(callSuper=false)
public class Student extends User {


    @ManyToMany(fetch=FetchType.EAGER,  cascade= {CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinTable(name="course_student", joinColumns={@JoinColumn(name="STUDENT_EMAIL",
            referencedColumnName = "email" )},
            inverseJoinColumns={@JoinColumn(name="COURSE_ID",
                    referencedColumnName = "course_id")})
    private List<Course> courses;


    public Student(String email, String password, String firstName, String lastName,
                   String gender) {
        super(email, password, firstName, lastName, gender);
    }

    public Student(){}

    public void addCourseForStudent(Course theCourse) {
        if(this.courses ==null) {
            this.courses = new ArrayList<>();
        }
        this.courses.add(theCourse);
        this.setCourses(courses);
    }

}
