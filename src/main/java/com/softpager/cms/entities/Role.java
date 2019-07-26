package com.softpager.cms.entities;

import com.softpager.cms.abstracts.CMSUser;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.*;

@Data
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue
    @Column(name = "role_id")
    private long id;

    @Column(unique = true)
    @NotEmpty
    private String name;


    @OneToMany(mappedBy = "roles", cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @ToString.Exclude
    private Set<CMSUser> users;

    public Role(String name) {
        this.name = name;
    }

    public Role() {
    }


}
