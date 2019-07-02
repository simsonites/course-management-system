package com.softpager.cms.abstracts;

import com.softpager.cms.entities.Course;
import com.softpager.cms.entities.Role;
import com.softpager.cms.entities.FileUpload;
import lombok.Data;
import lombok.ToString;


import javax.persistence.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "USER_GROUP")
public abstract class AbstractUser extends AuditModel {

    @Id
    @Email
    @NotEmpty
    @Column(unique = true)
    private String email;

    @NotEmpty
    private String password;

    @NotEmpty
    @Column(name = "first_name")
    private String firstName;

    @NotEmpty
    @Column(name = "last_name")
    private String lastName;

    @NotEmpty
    @Column(name = "gender")
    private String gender;


    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "photo_id")
    @ToString.Exclude
    private FileUpload photo;


    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinTable(name = "user_roles", joinColumns = {@JoinColumn(name = "USER_EMAIL",
            referencedColumnName = "email")},
            inverseJoinColumns = {@JoinColumn(name = "ROLES",
                    referencedColumnName = "name")})
    private Set<Role> roles = new HashSet<>();


    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST,
            CascadeType.REFRESH, CascadeType.DETACH})
    @JoinTable(name = "user_courses", joinColumns = {@JoinColumn(name = "USER_ID",
            referencedColumnName = "email")},
            inverseJoinColumns = {@JoinColumn(name = "COURSE_ID",
                    referencedColumnName = "course_id")})
    private Set<Course> courses = new HashSet<>();



    public AbstractUser(String email, String password, String firstName, String lastName,
                        String gender, FileUpload photo, Set<Role> roles) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.photo = photo;
        this.roles = roles;
    }

    public AbstractUser(String email, String password, String firstName, String lastName,
                        String gender, Set<Role> roles) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.roles = roles;
    }


    public AbstractUser(String email, String password, String firstName,
                        String lastName, String gender) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
    }

    public AbstractUser() {
    }

    public void addCourseForUser(Course theCourse){
        this.getCourses().add(theCourse);
        theCourse.getUsers().add(this);
    }

    public void removeCourseFromUser(Course theCourse){
        this.getCourses().remove(theCourse);
        theCourse.getUsers().remove(this);
    }

}
