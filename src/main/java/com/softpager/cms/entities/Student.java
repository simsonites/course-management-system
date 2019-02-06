package com.softpager.cms.entities;

import javax.persistence.Entity;
import javax.persistence.Table;


import com.softpager.cms.utils.AuditModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
public class Student extends AuditModel {

    @Id
    @Column(name="student_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="email")
    private String email;

    @Column(name="gender")
    private String gender;

    @ManyToMany(fetch=FetchType.EAGER, cascade= {CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinTable(name="course_student", joinColumns=@JoinColumn(name="student_id"),
            inverseJoinColumns=@JoinColumn(name="course_id"))
    //@JsonIgnoreProperties("student")
    private List<Course> courses;

    public Student() {	}

    public Student(String firstName, String lastName, String email, String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.courses = new ArrayList<>();
    }


    public void addCourseForStudent(Course theCourse) {
        if(this.courses ==null) {
            this.courses = new ArrayList<>();
        }
        this.courses.add(theCourse);
        this.setCourses(courses);
    }

}
