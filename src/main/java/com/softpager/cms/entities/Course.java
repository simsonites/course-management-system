package com.softpager.cms.entities;



import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import com.softpager.cms.utils.AuditModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;



@Data
@Entity
@Table(name="courses")
@SuppressWarnings("serial")
@EqualsAndHashCode(callSuper=false)
public class Course extends AuditModel {

    @Id
    @Column(name="course_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @Column(name="title")
    private String title;

    @Column(name="description")
    private String description = " Lorem Ipsum is simply dummy text of the printing\n" +
            "                        and typesetting industry.Lorem Ipsum has been the\n" +
            "                        industry's standard dummy text ever since the 1500s,\n" +
            "                        like Aldus PageMaker including versions of Lorem Ipsum.";

    @Column(name="credits")
    private int numberOfCredits;

    @ManyToMany(fetch=FetchType.LAZY,  cascade= {CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinTable(name="course_student", joinColumns=@JoinColumn(name="course_id"),
            inverseJoinColumns=@JoinColumn(name="student_id"))
    @JsonIgnoreProperties("course")
    private List<Student>listOfStudents;


    @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.DETACH,
            CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
    @JoinTable(name = "course_instructor", joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "instructor_id"))
    //@JsonIgnoreProperties("assignedCourses")
    @ToString.Exclude
    private Instructor instructor;

    public Course() {}

    public Course(String title, String description, int numberOfCredits) {
        this.title = title;
        this.description = description;
        this.numberOfCredits = numberOfCredits;
        this.listOfStudents = new ArrayList<>();
    }

}
