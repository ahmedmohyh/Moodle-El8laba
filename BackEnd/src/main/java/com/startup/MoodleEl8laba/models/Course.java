package com.startup.MoodleEl8laba.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Course {

    //<editor-fold desc="Properities">
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int courseId;


    @ManyToMany(targetEntity = User.class)
    private List<User> participant;

    @Column(name = "freeSeets", nullable = false,unique = false)
    private int freeSeets;

    @Column(name = "courseName", nullable = false,unique = false)
    private String courseName;

    @Column(name = "hasPassword", nullable = false,unique = false)
    private boolean hasPassword;

    @Column(name = "password", nullable = true,unique = false)
    private String password;


    @OneToOne
    private User creator;




//</editor-fold>


    //<editor-fold desc="Setter- Getter">


    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getFreeSeets() {
        return freeSeets;
    }

    public void setFreeSeets(int freeSeets) {
        this.freeSeets = freeSeets;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public boolean isHasPassword() {
        return hasPassword;
    }

    public void setHasPassword(boolean hasPassword) {
        this.hasPassword = hasPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }



    public List<User> getParticipant() {
        return participant;
    }

    public void setParticipant(List<User> participant) {
        this.participant = participant;
    }


//</editor-fold>


    //<editor-fold desc="To-string function">

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", freeSeets=" + freeSeets +
                ", courseName='" + courseName + '\'' +
                ", hasPassword=" + hasPassword +
                ", password='" + password + '\'' +
                ", creator=" + creator +
                '}';
    }

    //</editor-fold>

    //<editor-fold desc="Constructor">
    public Course(int courseId, List<User> participant, int freeSeets, String courseName, boolean hasPassword, String password, User creator) {
        this.courseId = courseId;
        this.participant = participant;
        this.freeSeets = freeSeets;
        this.courseName = courseName;
        this.hasPassword = hasPassword;
        this.password = password;
        this.creator = creator;
    }

    public Course() {

    }
    //</editor-fold>

}
