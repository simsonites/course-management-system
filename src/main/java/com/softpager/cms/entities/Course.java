package com.softpager.cms.entities;


import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.*;

import com.softpager.cms.abstracts.AbstractUser;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@Entity
@Table(name = "courses")
@SuppressWarnings("serial")
@EqualsAndHashCode(callSuper = false)
public class Course {

    @Id
    @GeneratedValue
    @Column(name = "course_id")
    private long id;

    @NotEmpty(message = "Please set  title for this course")
    @Column(name = "title", unique = true)
    private String title;

    @NotEmpty
    @Column(name = "description")
    private String description = " Lorem Ipsum is simply dummy text of the" +
            " printing and typesetting industry.Lorem Ipsum has been " +
            "the like Aldus PageMaker including versions of Lorem Ipsum.";


    @NotNull(message = "Please specify number of credits for this course")
    @Max(value = 30,message = "All courses have maximum of 30 credits")
    @Min(value = 15, message = "All courses have minimum of 15 credits")
    @Column(name = "credits")
    private int numberOfCredits;


    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST,
            CascadeType.REFRESH, CascadeType.DETACH})
    @JoinTable(name = "user_courses", joinColumns = {@JoinColumn(name = "COURSE_ID",
            referencedColumnName = "course_id")},
            inverseJoinColumns = {@JoinColumn(name = "USER_ID",
                    referencedColumnName = "user_id")})
    private List<AbstractUser> users = new ArrayList<>();


    public Course() {
    }


    public Course(String title, String description, int numberOfCredits) {
        this.title = title;
        this.description = description;
        this.numberOfCredits = numberOfCredits;
    }


    public void addUserToCourse(AbstractUser theUser){
        this.getUsers().add(theUser);
        theUser.getCourses().add(this);
    }


    public void removeUserFromCourse(AbstractUser theUser){
        this.getUsers().remove(theUser);
        theUser.getCourses().remove(this);
    }


    /*,
            CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH */

}