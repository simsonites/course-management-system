package com.softpager.cms.entities;

import javax.persistence.Entity;


import com.softpager.cms.abstracts.AbstractUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@SuppressWarnings("serial")
@EqualsAndHashCode(callSuper = false)
public class Student extends AbstractUser {


    public Student(String email, String password, String firstName, String lastName,
                   String gender) {
        super(email, password, firstName, lastName, gender);
    }

    public Student() {

    }

}
