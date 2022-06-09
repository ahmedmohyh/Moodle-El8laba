package com.startup.MoodleEl8laba.services;

import com.startup.MoodleEl8laba.models.Course;
import com.startup.MoodleEl8laba.models.User;
import com.startup.MoodleEl8laba.repository.CourseRepository;
import com.startup.MoodleEl8laba.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepo;

    @Autowired
    public CourseService(CourseRepository courseRepository, UserRepository userRepo) {
        this.courseRepository = courseRepository;
        this.userRepo = userRepo;
    }

    public List<Course> getAllUserCourses(int userId) {
        Optional<User> dbUser = this.userRepo.findById(userId);

        dbUser.get().toString();

        if (dbUser.isPresent())
             return this.courseRepository.findCoursesByParticipant(dbUser.get());
        else {
            System.out.println("The courses and user are null ");
            return null;
        }
    }

    public Course addUserToCourse(int courseId,int userId) throws Exception {

        Optional<User> dbUser = this.userRepo.findById(userId);
        Optional<Course> dbCourse = this.courseRepository.findById(courseId);

        if (dbUser.isPresent()) {

            dbUser.get().toString();

            if (dbCourse.isPresent())
            {
                dbCourse.get().toString();
                  dbCourse.get().getParticipant().add(dbUser.get());
                  this.courseRepository.save(dbCourse.get());
                //System.out.println("The function add user to course has given");
                //System.out.println(dbCourse.get().toString());
                return dbCourse.get();
            }
            else
                throw new Exception("The given course does not exist");

        }
        else
            throw new Exception("The given user does not exist please log in again");

    }


    public  Course addCourse (Course course, int creatorId) throws Exception {

        Optional<User> dbUser = this.userRepo.findById(creatorId);

        if (dbUser.isPresent()) {
            if (dbUser.get().getUserType() == User.UserType.TEACHER)
                course.setCreator(dbUser.get());
            else
                throw new Exception("The User of the course is not of the Role " + User.UserType.TEACHER);

            List<User> list= new ArrayList<>();
            list.add(dbUser.get());
            course.setParticipant(list);
            Course addedCourse = this.courseRepository.save(course);
            return  addedCourse;
        }
        throw  new Exception("The given creator does not exist");
    }
}
