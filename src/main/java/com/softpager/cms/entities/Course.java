package com.softpager.cms.entities;



import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import com.softpager.cms.utils.AuditModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;



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
    private String description;

    @Column(name="credits")
    private int numberOfCredits;

    @ManyToMany(fetch=FetchType.EAGER,  cascade= {CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinTable(name="course_student", joinColumns=@JoinColumn(name="course_id"),
            inverseJoinColumns=@JoinColumn(name="student_id"))
    @JsonIgnoreProperties("courses")
    private List<Student>students;


    @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.DETACH,
            CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
    @JoinTable(name = "course_instructor", joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "instructor_id"))
    @JsonIgnoreProperties("courses")
    private Instructor instructor;

    public Course() {}

    public Course(String title, String description, int numberOfCredits) {
        this.title = title;
        this.description = description;
        this.numberOfCredits = numberOfCredits;
        this.students = new ArrayList<>();
    }

}
