package com.startup.MoodleEl8laba.repository;

import com.startup.MoodleEl8laba.models.Course;
import com.startup.MoodleEl8laba.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Integer> {

    List<Course> findCoursesByParticipant(User user);

}
