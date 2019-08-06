package com.softpager.cms.entities;

import com.softpager.cms.abstracts.CMSUser;
import lombok.EqualsAndHashCode;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "admin")
@EqualsAndHashCode(callSuper = false)
public class Admin extends CMSUser {


    public Admin() {
    }

    public Admin(String firstName, String lastName, String gender) {
        super(firstName, lastName, gender);
    }
}
