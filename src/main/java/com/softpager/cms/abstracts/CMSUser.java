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
    @GeneratedValue
    private long id;

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

    @ManyToOne(fetch =FetchType.EAGER,cascade ={CascadeType.MERGE,CascadeType.PERSIST})
    @JoinColumn(name = "role_id")
    @ToString.Exclude
    private Role roles;


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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CMSUser)) return false;
        if (!super.equals(o)) return false;
        CMSUser cmsUser = (CMSUser) o;
        return getId() == cmsUser.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId());
    }

}
