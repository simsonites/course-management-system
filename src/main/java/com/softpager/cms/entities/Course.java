package com.softpager.cms.entities;



import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import com.softpager.cms.abstracts.AuditModel;
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
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "course_id")
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

    @ToString.Exclude
    @ManyToMany(fetch=FetchType.EAGER,  cascade= {CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinTable(name="course_student", joinColumns={@JoinColumn(name="COURSE_ID",
            referencedColumnName = "course_id" )},
            inverseJoinColumns={@JoinColumn(name="STUDENT_EMAIL",
                    referencedColumnName = "email")})
    private List<Student>listOfStudents;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private Instructor instructor;

    public Course() {}

    public Course(String title, String description, int numberOfCredits,
                  List<Student> listOfStudents, Instructor instructor) {
        this.title = title;
        this.description = description;
        this.numberOfCredits = numberOfCredits;
        this.listOfStudents = listOfStudents;
        this.instructor = instructor;
    }

    public Course(String title, String description, int numberOfCredits) {
        this.title = title;
        this.description = description;
        this.numberOfCredits = numberOfCredits;
        this.listOfStudents = new ArrayList<>();
    }

    public void addCourseForUser(List<Course> theCourse, Student theStudent){
        if (this.getListOfStudents().isEmpty()){
            this.listOfStudents = new ArrayList<>();
            this.listOfStudents.add(theStudent);
            theStudent.setCourses(theCourse);
        }

    }

}
