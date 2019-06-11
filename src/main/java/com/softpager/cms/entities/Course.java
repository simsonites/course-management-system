package com.softpager.cms.entities;


import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;



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

    @NotEmpty
    @Column(name = "title")
    private String title;

    @NotEmpty
    @Column(name = "description")
    private String description = " Lorem Ipsum is simply dummy text of the" +
            " printing and typesetting industry.Lorem Ipsum has been " +
            "the like Aldus PageMaker including versions of Lorem Ipsum.";

   // @NotEmpty
    @Column(name = "credits")
    private int numberOfCredits;


    @ToString.Exclude
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REFRESH,
            CascadeType.DETACH})
    @JoinTable(name = "course_student", joinColumns = {@JoinColumn(name = "COURSE_ID",
            referencedColumnName = "course_id")},
            inverseJoinColumns = {@JoinColumn(name = "USER_EMAIL",
                    referencedColumnName = "email")})
    private List<Student> students;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private Instructor instructor;

    public Course() {
    }

    public Course(String title, String description, int numberOfCredits) {
        this.title = title;
        this.description = description;
        this.numberOfCredits = numberOfCredits;

    }

    public void removeCourseFromStudent(Student theStudent){
        this.getStudents().remove(theStudent);
        theStudent.getCourses().remove(this);
    }


}