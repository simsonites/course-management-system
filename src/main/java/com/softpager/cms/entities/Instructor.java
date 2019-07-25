package com.softpager.cms.entities;

import com.softpager.cms.abstracts.CMSUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class Instructor extends CMSUser {

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
