package com.softpager.cms.entities;

import com.softpager.cms.abstracts.User;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "instructors")
public class Instructor extends User {


    private String title;

    @OneToMany(mappedBy="instructor", cascade = {CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH})
    private Set<Course> courses = new HashSet<>();


    public Instructor(String email, String password, String firstName,
                      String lastName,String gender, String title) {
        super(email, password, firstName, lastName, gender);
        this.title = title;
    }

    public Instructor() {
    }
}
