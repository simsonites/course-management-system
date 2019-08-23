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

    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST,
                    CascadeType.REFRESH, CascadeType.DETACH})
    private List<CMSUser> users = new ArrayList<>();


    public Role(String name, List<CMSUser> users) {
        this.name = name;
        this.users = users;
    }

    public Role(String name) {
        this.name = name;
    }

    public Role() {
    }


    public void addRoleToUSer(CMSUser theUser){
        this.getUsers().add(theUser);
        theUser.getRoles().add(this);
    }



}