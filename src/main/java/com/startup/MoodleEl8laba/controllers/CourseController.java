package com.startup.MoodleEl8laba.controllers;

import com.startup.MoodleEl8laba.models.Course;
import com.startup.MoodleEl8laba.models.User;
import com.startup.MoodleEl8laba.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

   private  final CourseService courseService;
    @Autowired
    public CourseController(CourseService cs) {
        this.courseService = cs;
    }

    @GetMapping("/{userid}")
    public ResponseEntity<List<com.startup.MoodleEl8laba.models.Course>> getAllCourses (@PathVariable int userid) {

        System.out.println(" the user id is" + userid);
        //System.out.println("from the userNameExist the userName is " + userName);
        return new ResponseEntity<>(this.courseService.getAllUserCourses(userid), HttpStatus.OK);

    }

    @PutMapping("/{courseId}/{userId}")
    public ResponseEntity<?>  addUserToCourse(@PathVariable int courseId, @PathVariable int userId) {
        System.out.println("The course Id is " + courseId + " the user id is" + userId  );
        try {
            return new ResponseEntity<>(this.courseService.addUserToCourse(courseId,userId), HttpStatus.OK);
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
