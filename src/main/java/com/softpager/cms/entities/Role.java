package com.softpager.cms.entities;

import com.softpager.cms.abstracts.AbstractUser;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @Column(unique = true)
    @NotEmpty
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<AbstractUser> users = new HashSet<>();

    public Role(String name, Set<AbstractUser> users) {
        this.name = name;
        this.users = users;

    }

    public Role(String name) {
        this.name = name;
    }

    public Role() {
    }
}
