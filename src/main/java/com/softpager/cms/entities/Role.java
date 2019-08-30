package com.softpager.cms.entities;

import com.softpager.cms.abstracts.AbstractUser;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue
    @Column(name = "role_id")
    private long id;

    @Column(unique = true)
    @NotEmpty
    private String name;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER,
            cascade = {CascadeType.MERGE})
    private List<AbstractUser> users = new ArrayList<>();


    public Role(String name, List<AbstractUser> users) {
        this.name = name;
        this.users = users;
    }

    public Role(String name) {
        this.name = name;
    }

    public Role() {
    }

    @Override
    public String getAuthority() {
        return name;
    }

    public void addRoleToUSer(AbstractUser theUser){
        this.getUsers().add(theUser);
        theUser.getRoles().add(this);
    }

}