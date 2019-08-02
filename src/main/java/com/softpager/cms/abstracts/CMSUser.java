package com.softpager.cms.abstracts;

import com.softpager.cms.entities.Course;
import com.softpager.cms.entities.Role;
import com.softpager.cms.entities.FileUpload;
import lombok.Data;
import lombok.ToString;


import javax.persistence.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.*;

@Data
@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "USER_GROUP")
public abstract class CMSUser extends AuditModel {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Email(message = "please enter a valid email address")
    @NotEmpty(message = "email is needed")
    @Column(unique = true)
    private String email;

    @NotEmpty(message = "please set a password")
    private String password;

    @NotEmpty(message = "first name is required")
    @Column(name = "first_name")
    private String firstName;

    @NotEmpty(message = "last name is required")
    @Column(name = "last_name")
    private String lastName;

    @NotEmpty(message = "please specify gender")
    @Column(name = "gender")
    private String gender;


    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "photo_id")
    @ToString.Exclude
    private FileUpload photo;

    @ManyToOne(fetch = FetchType.EAGER, cascade ={CascadeType.MERGE})
    @JoinColumn(name = "role_id")
    private Role role;


    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST,
            CascadeType.REFRESH, CascadeType.DETACH})
    @JoinTable(name = "user_courses", joinColumns = {@JoinColumn(name = "USER_ID",
            referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "COURSE_ID",
                    referencedColumnName = "course_id")})
    private Set<Course> courses = new HashSet<>();


    public CMSUser() {
    }

    public CMSUser(String email, String password, String firstName,
                   String lastName, String gender) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
    }

}
