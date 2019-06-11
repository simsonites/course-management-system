package com.softpager.cms.entities;

import javax.persistence.Entity;
import javax.persistence.Table;


import com.softpager.cms.abstracts.AbstractUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
@Table(name = "students")
@SuppressWarnings("serial")
@EqualsAndHashCode(callSuper = false)
public class Student extends AbstractUser {


    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinTable(name = "course_student", joinColumns = {@JoinColumn(name = "USER_EMAIL",
            referencedColumnName = "email")},
            inverseJoinColumns = {@JoinColumn(name = "COURSE_ID",
                    referencedColumnName = "course_id")})
    private List<Course> courses;



    public Student(String email, String password, String firstName, String lastName,
                   String gender) {
        super(email, password, firstName, lastName, gender);
    }

    public Student() {
    }

}
