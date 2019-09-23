package com.softpager.cms.entities;

import com.softpager.cms.abstracts.AbstractUser;
import lombok.Data;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

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
    @ToString.Exclude
    private List<AbstractUser> users = new ArrayList<>();


    public Role(String name, List<AbstractUser> users) {
        this.name = name;
        this.users = users;
    }

    public Role(String name) {
        this.name = name;
    }

    public Role() {}

    @Override
    public String getAuthority() {
        return name;
    }

    public void addRoleToUSer(AbstractUser theUser){
        this.getUsers().add(theUser);
        theUser.getRoles().add(this);
    }

    public void removeUserFromRoles(AbstractUser theUser){
        Set<Role> userRoles = theUser.getRoles();
        userRoles.removeIf(Objects::nonNull);
    }

}