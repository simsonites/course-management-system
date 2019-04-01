package com.softpager.cms.entities;

import com.softpager.cms.abstracts.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name="admin")
@EqualsAndHashCode(callSuper = false)
public class Admin extends User {

    public Admin(String email, String password, String firstName,
                 String lastName, String gender) {
        super(email, password, firstName, lastName, gender);
    }

    public Admin() {
    }
}
