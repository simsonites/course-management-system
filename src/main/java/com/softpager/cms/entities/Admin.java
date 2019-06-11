package com.softpager.cms.entities;

import com.softpager.cms.abstracts.AbstractUser;
import lombok.EqualsAndHashCode;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "admin")
@EqualsAndHashCode(callSuper = false)
public class Admin extends AbstractUser {


    public Admin() {
    }

    public Admin(String email, String password, String firstName, String lastName,
                 String gender) {
        super(email, password, firstName, lastName, gender);
    }
}
