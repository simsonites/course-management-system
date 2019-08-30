package com.softpager.cms;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseManagementSystemApplicationTests {

/*

 @Autowired
    private StudentService studentService;
 @Autowired
 private InstructorService instructorService;
 @Autowired
 private CourseService courseService;

    @Before
    public void initDB(){

        {
            Student theStudent = new Student("abby@email.com","123456", "Abby", "Sam", "M");
            studentService.saveStudent(theStudent);
        }
        {
            Instructor theInstructor = new Instructor("weng@email.com", "123456", "Weng", "Xuka", "F", "Dr");
            instructorService.createInstructor(theInstructor);
        }
    }

    @Test
    public void testRetrieve() {

      Student theStudent = studentService.getStudent("abby@email.com");
        assertNotNull(theStudent);

        Instructor theInstructor = instructorService.getInstructor("weng@email.com");
        assertEquals(theInstructor.getEmail(), "weng@email.com");
    }
*/


  /*  @Test
    public void saveCourse() {
   *//*     {
            Course course1 = new Course("Practical Spring and Hibernate For beginners", "Some Programming Coding Skills", 13);
            courseService.saveCourse(course1);
        }
        {
            Course  course2 = new Course("Introduction to MYSQL Programming", "Some Programming Coding Skills", 15);
            courseService.saveCourse(course2);

        }*//*
    }


    @Test
    public void addCourseToStudent(){
      *//*  {
            Course theCourse = courseService.getCourse(7);
            Student theStudent = studentService.getStudent("beth@email.com");
            courseService.addCourseForStudent(theCourse, theStudent);
        }*//*
    }

    @Test
    public void getStudentCourses(){
     *//*  {
           Student theStudent = studentService.getStudent("beth@email.com");
            List<Course> courses = courseService.getStudentCourses(theStudent.getEmail());
            assertNotNull(courses);
        }*//*
    }*/


    @Test
    public void load() {
    }

}

