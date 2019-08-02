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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    @NotEmpty(message = "please, enter a new role to be created")
    private String name;


    @OneToMany(mappedBy = "role", fetch = FetchType.EAGER,cascade ={CascadeType.MERGE})
    private List<CMSUser> users;

    public Role(String name) {
        this.name = name;
    }

    public Role() {
    }

    public void addUser(CMSUser theUser) {
        if (users == null) {
            users = new ArrayList<>();
        }
        users.add(theUser);
        theUser.setRole(this);
    }

    public void removeUser(CMSUser theUsers){
        users.remove(theUsers);
        theUsers.setRole(null);
    }
}