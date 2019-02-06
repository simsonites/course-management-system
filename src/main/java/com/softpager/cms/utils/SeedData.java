package com.softpager.cms.utils;

import com.softpager.cms.entities.Instructor;
import com.softpager.cms.entities.Student;
import com.softpager.cms.repositories.InstructorRepository;
import com.softpager.cms.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Component
public class SeedData {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private InstructorRepository instructorRepository;

     private List<Student> initStudents = Arrays.asList(
            new Student("Sam", "Jefferson", "jeffer@email.com", "M"),
            new Student("Rosemary", "Moore", "moore@email.com", "F"),
            new Student("Janice", "Manissa", "jane@email.com", "F"),
            new Student("Sally", "Jefferson", "sally@email.com", "F"),
            new Student("Jessica", "Private", "jess@email.com", "F"),
            new Student("Jane", "Public", "jane@email.com", "F"),
            new Student("Pete", "Markson", "pete@email.com", "M"),
            new Student("Jefferson", "Moore", "jeff@email.com", "M"),
            new Student("Alex", "Forrest", "alex@email.com", "M"),
            new Student("Amanda", "Yvonne", "amanda@email.com", "F"),
            new Student("Anita", "World", "anita@email.com", "F"),
            new Student("Alice", "Morgan", "alice@email.com", "F"),
            new Student("Mary", "Private", "mary@email.com", "F"),
            new Student("Elsa", "Vietnam", "elas@email.com", "F"),
            new Student("Emmy", "Hung Yen", "emmy@email.com", "F"),
            new Student("Emma", "Pretty", "ema@email.com", "F"),
            new Student("Leo", "Wilson", "leo@email.com", "M"),
            new Student("Misa", "Protected", "mis@email.com", "F"),
            new Student("Lisa", "Jumia", "lis@email.com", "F"),
            new Student("Tracy", "Jumia", "tracy@email.com", "F"),
            new Student("Akon", "Konvict", "konv@email.com", "M"),
            new Student("Ricky", "Ross", "rick@email.com", "M"),
            new Student("Vicky", "Pope", "lis@email.com", "F"),
            new Student("Rose", "Van", "van@email.com", "F"),
            new Student("Mavin", "Gate", "mavi@email.com", "F"),
            new Student("Bill", "Gates", "bill@email.com", "M"),
            new Student("Lisa", "Diamond", "lisdia@email.com", "F"),
            new Student("Alexander", "Offison", "alexa@email.com", "M")
            );


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

    );

/*
    @PostConstruct
    public void doSave(){
        this.saveStudents();
        this.saveInstructors();
    }*/

    private void saveStudents(){
        studentRepository.saveAll(initStudents);
    }


    private void saveInstructors(){
        instructorRepository.saveAll(initInstructors);
    }


}
