package com.lambdaschool.school.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambdaschool.school.SchoolApplication;
import com.lambdaschool.school.model.Course;
import com.lambdaschool.school.model.Instructor;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;

import javax.persistence.EntityNotFoundException;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = SchoolApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CourseServiceImplTest {

    @Autowired
    private CourseService courseservice;

    @Autowired
    private InstructorService instructorService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void initialiseRestAssuredMockMvcWebApplicationContext()
    {
        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void findAll() {
        assertEquals(6, courseservice.findAll().size());
    }

    @Test
    public void getCountStudentsInCourse() {
    }

    @Test (expected = EntityNotFoundException.class)
    public void deleteNotFound()
    {
        courseservice.delete(100);
        assertEquals(6, courseservice.findAll().size());
    }

    @Test
    public void deleteFound() {
        courseservice.delete(2);
        assertEquals(5, courseservice.findAll().size());
    }

    @Test
    public void findCourseById() {

        Course course =  courseservice.findCourseById(1);
        assertEquals("Data Science", course.getCoursename());

    }

//    @Test
//    public void addCourseTest()
//    {
//        Instructor Sally = instructorService.getInstructorById(1);
//        System.out.println(Sally.toString());
//        Course newCourse = new Course("test", Sally);
//        System.out.println(newCourse.toString());
//        courseservice.save(newCourse);
//
//        assertEquals(7, courseservice.findAll().size());
//
//    }

    @Test
    public void newCourseTest() throws Exception
    {
        Instructor Sally = instructorService.getInstructorById(1);
        System.out.println(Sally.toString());
        Course newCourse = new Course("test", Sally);


        ObjectMapper mapper = new ObjectMapper();
        String stringNewCourse = mapper.writeValueAsString(newCourse);
        System.out.println(stringNewCourse);

        given().contentType("application/json").body(stringNewCourse).when().post("/courses/course/add").then().statusCode(201);
    }
}