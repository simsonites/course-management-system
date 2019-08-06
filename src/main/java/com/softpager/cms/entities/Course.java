package com.softpager.cms.entities;


import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.*;

import com.softpager.cms.abstracts.CMSUser;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@Entity
@Table(name = "courses")
@SuppressWarnings("serial")
@EqualsAndHashCode(callSuper = false)
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private long id;

    @NotEmpty(message = "course title required")
    @Column(name = "title", unique = true)
    private String title;

    @NotEmpty
    @Column(name = "description")
    private String description = " Lorem Ipsum is simply dummy text of the" +
            " printing and typesetting industry.Lorem Ipsum has been " +
            "the like Aldus PageMaker including versions of Lorem Ipsum.";


    @Min(value = 15, message = "number of credits must not below 15")
    @Max(value = 30, message = "number of credits must not above 30")
    @Column(name = "credits")
    private int numberOfCredits;


    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST,
            CascadeType.REFRESH, CascadeType.DETACH})
    @JoinTable(name = "student_courses", joinColumns = {@JoinColumn(name = "COURSE_ID",
            referencedColumnName = "course_id")},
            inverseJoinColumns = {@JoinColumn(name = "STUDENT_ID",
                    referencedColumnName = "user_id")})
    private List<Student> students = new ArrayList<>();


   public Course() {
    }


    public Course(String title, String description, int numberOfCredits) {
        this.title = title;
        this.description = description;
        this.numberOfCredits = numberOfCredits;
    }


    public void addStudentToCourse(Student theStudent){
        this.getStudents().add(theStudent);
        theStudent.getCourses().add(this);
    }


    public void removeStudentFromCourse(Student theStudent){
        this.getStudents().remove(theStudent);
        theStudent.getCourses().remove(this);
    }


}