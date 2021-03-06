package com.softpager.cms.entities;

import com.softpager.cms.abstracts.AbstractUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class Instructor extends AbstractUser {

    @Column(name = "Qualification")
    private String title;


    public Instructor(String email, String password, String firstName,
                      String lastName, String gender, String title) {
        super(email, password, firstName, lastName, gender);
        this.title = title;
    }

    public Instructor() {
    }
}
