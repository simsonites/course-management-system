package com.softpager.cms.entities;

import javax.persistence.*;


import com.softpager.cms.abstracts.CMSUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@SuppressWarnings("serial")
@EqualsAndHashCode(callSuper = false)
public class Student extends CMSUser {



    public Student(String firstName, String lastName, String gender) {
        super(firstName, lastName, gender);
    }

    public Student() {

    }



}
