package com.startup.MoodleEl8laba.controllers;

import com.startup.MoodleEl8laba.models.Course;
import com.startup.MoodleEl8laba.models.User;
import com.startup.MoodleEl8laba.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/courses")
public class CourseController {

   private  final CourseService courseService;
    @Autowired
    public CourseController(CourseService cs) {
        this.courseService = cs;
    }

    @GetMapping("/enrolledCourses/{userid}")
    public ResponseEntity<?> getAllCourses (@PathVariable int userid) {

        System.out.println(" the user id is" + userid);
        //System.out.println("from the userNameExist the userName is " + userName);
        try {
            return new ResponseEntity<>(this.courseService.getAllUserEnrolledCourses(userid), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    @GetMapping("/freeCourses/{userid}")
    public ResponseEntity<?> getFreeCourses (@PathVariable int userid) {

        System.out.println(" the user id is" + userid);
        //System.out.println("from the userNameExist the userName is " + userName);
        try {
            return new ResponseEntity<>(this.courseService.getAllUserFreeCourses(userid), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    @GetMapping("/{courseId}")
    public ResponseEntity<?> getCourse (@PathVariable int courseId ) {

        try {
            return new ResponseEntity<>(this.courseService.getCourse(courseId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/isRegistered/{courseId}/{userId}")
    public ResponseEntity<?> isUserRegisteredAtCourse (@PathVariable int courseId,@PathVariable int userId ) {

        try {
            return new ResponseEntity<>(this.courseService.isUserRegisteredAtCourse(courseId,userId), HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
    @PutMapping("register/{courseId}/{userId}")
    public ResponseEntity<?>  addUserToCourse(@PathVariable int courseId, @PathVariable int userId, @RequestBody Optional<String> password ) {
        System.out.println("The course Id is " + courseId + " the user id is" + userId  );
        try {
            return new ResponseEntity<>(this.courseService.addUserToCourse(courseId,userId, password), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PostMapping("/add/{creatorId}")
    public ResponseEntity<?> addCourse(@RequestBody Course course , @PathVariable int creatorId) {

        System.out.println("The course Id is " + creatorId + " the user id is" + course.toString()  );
        try {
            return new ResponseEntity<>(this.courseService.addCourse(course,creatorId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>( e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
        //return  this.courseService.addUserToCourse(courseId,userId);
    }



}
