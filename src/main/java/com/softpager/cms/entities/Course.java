package com.softpager.cms.entities;


import java.util.*;
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
    @Max(value = 30, message = "All courses have maximum of 30 credits")
    @Min(value = 15, message = "All courses have minimum of 15 credits")
    @Column(name = "credits")
    private int numberOfCredits;


    @ManyToMany(mappedBy = "courses", cascade = {CascadeType.PERSIST,
            CascadeType.MERGE, CascadeType.DETACH},
            fetch = FetchType.LAZY)
    private List<AbstractUser> users = new ArrayList<>();


    public Course() {
    }

    public Course(String title, String description, int numberOfCredits) {
        this.title = title;
        this.description = description;
        this.numberOfCredits = numberOfCredits;
    }

    /****THIS METHOD IS CALL WHEN A COURSE COURSES ARE TO BE ADDED FOR A USER****/
    public void addUserToCourse(AbstractUser theUser) {
        this.getUsers().add(theUser);
        theUser.getCourses().add(this);
    }

    /****THIS METHOD IS CALL WHEN A COURSE IS TO BE REMOVED FROM A USER****/

    public void removeUserFromCourse(AbstractUser theUser) {
        this.getUsers().remove(theUser);
        theUser.getCourses().remove(this);
    }

    /***THE METHOD IS CALL WHEN ALL COURSES ARE TO REMOVED FROM A USER***/
    public void breakUserRelationship(AbstractUser theUser) {
        Set<Course> userCourses = theUser.getCourses();
        userCourses.removeIf(Objects::nonNull);
    }

    /****THIS METHOD IS CALL WHEN A COURSE IS TO BE DELETED FROM THE DATABASE****/
    @PreRemove
    public void breakRelationshipToDelete(){
        for (AbstractUser user : users){
            user.getCourses().remove(this);
        }
    }


}