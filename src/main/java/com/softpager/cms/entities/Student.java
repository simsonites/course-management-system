package com.softpager.cms.entities;

import javax.persistence.Entity;


import com.softpager.cms.abstracts.CMSUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@SuppressWarnings("serial")
@EqualsAndHashCode(callSuper = false)
public class Student extends CMSUser {


    public Student(String email, String password, String firstName, String lastName,
                   String gender) {
        super(email, password, firstName, lastName, gender);
    }

    public Student() {

    }



}
