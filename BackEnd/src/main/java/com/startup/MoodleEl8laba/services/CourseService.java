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
import java.util.stream.Collectors;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final UserRepository userRepo;

    @Autowired
    public CourseService(CourseRepository courseRepository, UserRepository userRepo) {
        this.courseRepository = courseRepository;
        this.userRepo = userRepo;
    }

    public List<Course> getAllUserEnrolledCourses(int userId) throws Exception {
        Optional<User> dbUser = this.userRepo.findById(userId);

        if (dbUser.isPresent())
            return this.courseRepository.findCoursesByParticipant(dbUser.get());

        else {
            System.out.println("The courses and user are null ");
            throw new Exception("The User does not exist");
        }
    }

    public List<Course> getAllUserFreeCourses(int userId) throws Exception {
        Optional<User> dbUser = this.userRepo.findById(userId);

        if (dbUser.isPresent()) {
            List<Course> freeCoruses = this.courseRepository.findAll().stream()
                    .filter(course -> !course.getParticipant().contains(dbUser.get())).collect(Collectors.toList());

            return freeCoruses;
        } else {
            System.out.println("The courses and user are null ");
            throw new Exception("The User does not exist, please log in first");
        }
    }

    /***
     * @param courseId
     * @param userId
     * @return
     * @throws Exception
     */
    public Course addUserToCourse(int courseId, int userId, Optional<String> password) throws Exception {

        Optional<User> dbUser = this.userRepo.findById(userId);
        Optional<Course> dbCourse = this.courseRepository.findById(courseId);

        if (dbUser.isPresent()) {

            dbUser.get().toString();

            if (dbCourse.isPresent()) {

                System.out.println("Password of the user "+ password);

                if (password.isPresent() && dbCourse.get().isHasPassword() && !password.equals(dbCourse.get().getPassword()))
                    throw new Exception("Key of the course is incorrect");

                if (dbCourse.get().getFreeSeets() ==0)
                    throw new Exception("No seets remaining, please contact the chair");

                dbCourse.get().getParticipant().add(dbUser.get());
                dbCourse.get().setFreeSeets(dbCourse.get().getFreeSeets()-1);

                this.courseRepository.save(dbCourse.get());

                return dbCourse.get();
            } else
                throw new Exception("The given course does not exist");

        } else
            throw new Exception("The given user does not exist please log in again");

    }
    public Course addCourse(Course course, int creatorId) throws Exception {

        Optional<User> dbUser = this.userRepo.findById(creatorId);

        if (dbUser.isPresent()) {
            if (dbUser.get().getUserType() == User.UserType.TEACHER)
                course.setCreator(dbUser.get());
            else
                throw new Exception("The User of the course is not of the Role " + User.UserType.TEACHER);

            List<User> list = new ArrayList<>();
            list.add(dbUser.get());
            course.setParticipant(list);
            Course addedCourse = this.courseRepository.save(course);
            return addedCourse;
        }
        throw new Exception("The given creator does not exist");
    }

    /***
     * @param courseId
     * @param userId
     * @return
     * @throws Exception
     */
    public boolean isUserRegisteredAtCourse(int courseId, int userId) throws Exception {

        Optional<Course> dbCourse = this.courseRepository.findById(courseId);

        if (dbCourse.isPresent()) {

            return dbCourse.get().getParticipant().stream()
                    .filter(user -> user.getUserId() == userId).count() > 0;
        } else {

            throw new Exception("the course with the courseId: " + courseId + " does not exist");
        }

    }

    /**
     * @param courseId
     * @return
     * @throws Exception
     */
    public Course getCourse(int courseId) throws Exception {

        Optional<Course> dbCourse = this.courseRepository.findById(courseId);

        if (!dbCourse.isPresent())
            throw new Exception("The course with the Id" + courseId + "does not exist");

        else
            return dbCourse.get();
    }
}
