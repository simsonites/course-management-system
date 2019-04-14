package com.softpager.cms.utils;

import com.softpager.cms.entities.Course;
import com.softpager.cms.entities.Student;
import com.softpager.cms.repositories.CourseRepository;
import com.softpager.cms.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;



@Component
public class SeedData {

    private  String description="Lorem Ipsum is simply dummy text of the printing " +
            "and typesetting industry. Lorem Ipsum has been the " +
            "industry's standard dummy text ever since the 1500s," +
            " when an unknown printer took a galley of type and " +
            "scrambled it to make a type ";


    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

     private List<Student> initStudents = Arrays.asList(
            new Student("jeffer@email.com","1234567","Sam", "Jefferson", "M"),
            new Student("moore@email.com","1234567","Rosemary", "Moore", "F"),
            new Student("jane@email.com","1234567","Janice","Manissa", "F"),
            new Student("sally@email.com","1234567","Sally", "Jefferson","F"),
            new Student("jess@email.com", "1234567","Jessica", "Private", "F"),
            new Student("jane@email.com", "1234567","Jane", "Public", "F"),
            new Student("pete@email.com","1234567","Pete", "Markson", "M"),
            new Student("jeff@email.com","1234567","Jefferson", "Moore", "M"),
            new Student( "alex@email.com", "1234567","Alex", "Forrest","M"),
            new Student("amanda@email.com", "1234567","Amanda", "Yvonne", "F"),
            new Student("anita@email.com", "1234567","Anita", "World", "F"),
            new Student("alice@email.com", "1234567","Alice", "Morgan", "F"),
            new Student("mary@email.com", "1234567","Mary", "Private","F"),
            new Student( "elas@email.com", "1234567","Elsa", "Vietnam","F"),
            new Student("emmy@email.com", "1234567","Emmy", "Hung Yen", "F"),
            new Student("ema@email.com", "1234567","Emma", "Pretty", "F"),
            new Student("leo@email.com","1234567","Leo", "Wilson",  "M"),
            new Student("mis@email.com","1234567","Misa", "Protected",  "F"),
            new Student("lis@email.com", "1234567","Lisa", "Jumia", "F"),
            new Student("tracy@email.com","1234567","Tracy", "Jumia",  "F"),
            new Student("konv@email.com","1234567","Akon", "Konvict", "M"),
            new Student("rick@email.com", "1234567","Ricky", "Ross","M"),
            new Student("lis@email.com","1234567","Vicky", "Pope",  "F"),
            new Student("van@email.com", "1234567","Rose", "Van","F"),
            new Student("mavi@email.com","1234567","Mavin", "Gate",  "F"),
            new Student("bill@email.com", "1234567","Bill", "Gates", "M"),
            new Student("lisdia@email.com", "1234567","Lisa", "Diamond", "F"),
            new Student("alexa@email.com", "1234567","Alexander", "Offison","M")
            );
/*

    private List<Instructor> initInstructors = Arrays.asList(
            new Instructor("Alexander", "Offison", "001-541-754-3010", "off@email.com", "M", "Prof"),
            new Instructor("Chad", "Derby", "001-541-754-3010", "derby@email.com", "M", "Prof"),
            new Instructor("Felicity", "Feminite", "001-541-754-3010", "fem@email.com", "F", "Prof"),
            new Instructor("Jubilee", "Feminite", "001-541-754-3010", "fem@email.com", "F", "Prof"),
            new Instructor("Andrea", "Putin", "001-541-754-3010", "put@email.com", "F", "Dr"),
            new Instructor("Justin", "Muska", "001-541-754-3010", "mus@email.com", "M", "Dr"),
            new Instructor("Sam", "Jefferson", "001-541-754-3010", "jeffer@email.com", "M", "Prof"),
            new Instructor("Alex", "Lessa", "001-541-754-3010", "lisa@email.com", "M", "Prof"),
            new Instructor("Leslay", "Brown", "001-541-754-3010", "bro@email.com", "M", "Prof"),
            new Instructor("Wine", "Oranger", "001-541-754-3010", "oran@email.com", "M", "Prof"),
            new Instructor("Skinn", "Rhoder", "001-541-754-3010", "rho@email.com", "M", "Dr"),
            new Instructor("Rufus", "Panter", "001-541-754-3010", "pant@email.com", "M", "Prof"),
            new Instructor("Johnson", "John", "001-541-754-3010", "john@email.com", "M", "Prof"),
            new Instructor("Simon", "Cowell", "001-541-754-3010", "cowel@email.com", "M", "Dr"),
            new Instructor("Lakshmi", "Jaya", "001-541-754-3010", "jaya@email.com", "F", "Prof"),
            new Instructor("Silly", "Lecturer", "001-541-754-3010", "lec@email.com", "M", "Prof"),
            new Instructor("Mazda", "Good", "001-541-754-3010", "good@email.com", "M", "Prof"),
            new Instructor("David", "Bidem", "001-541-754-3010", "bid@email.com", "M", "Prof"),
            new Instructor("Layla", "Vietnam", "001-541-754-3010", "viet@email.com", "F", "Dr"),
            new Instructor("Selena", "Shore", "001-541-754-3010", "shore@email.com", "F", "Prof"),
            new Instructor("Sophia", "Pretty", "001-541-754-3010", "pret@email.com", "F", "Dr"),
            new Instructor("Erica", "Public", "001-541-754-3010", "pub@email.com", "F", "Prof")
    );*/


    private List<Course> initCourses = Arrays.asList(
            new Course("A Practical guide to Java Programming", this.description, 30),
            new Course("Oracle Database Development", this.description, 15),
            new Course("A Practical guide to SQL Programming", this.description, 30),
            new Course("A Practical guide to OOP in Java", this.description, 30),
            new Course("Programming with Spring Frameworks", this.description, 30),
            new Course("A Practical guide to Hibernate ORM", this.description, 25),
            new Course("Programming Frameworks", this.description, 15),
            new Course("Mobile Apps Development with Java ", this.description, 30),
            new Course("Introduction to MYSQL Database", this.description, 15),
            new Course("Practical guide to C++ Programming", this.description, 30),
            new Course("Working with Git (A Practical guide)", this.description, 30),
            new Course("Programming in PHP", this.description, 25),
            new Course("AWS Architecture", this.description, 15),
            new Course("Web Development with HTML,CSS an JavaScript", this.description, 30),
            new Course("Java Annotations", this.description, 15)
    );


   // @PostConstruct
    public void doSave(){
        this.saveStudents();
        saveCourses();
       //return "redirect:/students";
    }

   private void saveStudents(){
      studentRepository.saveAll(initStudents);
    }

    private void doSaveStudent(){

    }

    private void saveCourses(){
      courseRepository.saveAll(initCourses);
    }


}
