package com.softpager.cms.abstracts;

import com.softpager.cms.entities.Role;
import com.softpager.cms.entities.UserPhoto;
import lombok.Data;
import lombok.ToString;


import javax.persistence.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "userClass")
public abstract class User extends AuditModel {

    @Id
    @Email
    @NotEmpty
    @Column(unique=true)
    private String email;

    @NotEmpty
    private String password;

    @NotEmpty
    @Column(name="first_name")
    private String firstName;

    @NotEmpty
    @Column(name="last_name")
    private String lastName;

    @NotEmpty
    @Column(name="gender")
    private String gender;


    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="photo_id")
    @ToString.Exclude
    private UserPhoto photo;

    @ManyToMany(fetch=FetchType.LAZY,  cascade= {CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinTable(name="user_roles", joinColumns={@JoinColumn(name="USER_EMAIL",
            referencedColumnName = "email" )},
            inverseJoinColumns={@JoinColumn(name="ROLES",
                    referencedColumnName = "name")})
    private Set<Role> roles = new HashSet<>();



    public User(String email, String password, String firstName, String lastName,
                String gender, UserPhoto photo, Set<Role> roles) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.photo = photo;
        this.roles = roles;
    }

    public User(String email, String password, String firstName, String lastName,
                String gender, Set<Role> roles) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.roles = roles;
    }


    public User(String email, String password, String firstName,
                String lastName, String gender) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
    }

    public User(){}
}
