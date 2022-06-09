package com.startup.MoodleEl8laba.controllers;

import com.startup.MoodleEl8laba.models.Course;
import com.startup.MoodleEl8laba.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<com.startup.MoodleEl8laba.models.Course> getAllCourses (@PathVariable int userid) {

        System.out.println(" the user id is" + userid);
        //System.out.println("from the userNameExist the userName is " + userName);
        return  this.courseService.getAllUserCourses(userid);
    }

    @PostMapping("/{courseId}/{userId}")
    public Course  addUserToCourse(@PathVariable int courseId, @PathVariable int userId) {
        System.out.println("The course Id is " + courseId + " the user id is" + userId  );
        return  this.courseService.addUserToCourse(courseId,userId);
    }

}
