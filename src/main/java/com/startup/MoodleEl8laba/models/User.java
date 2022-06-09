package com.startup.MoodleEl8laba.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="moodle_users")
public class User {

    //<editor-fold desc="Properities">
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int userId;

    @Column(name = "firstName", nullable = false,unique = false)
    private String firstName;

    @Column(name = "lastName", nullable = false,unique = false)
    private String lastName;

    @Column(name = "userName", nullable = false,unique = true)
    private String userName;

    @Column(name = "password", nullable = false,unique = false)
    private String password;

    @Column(name = "mail", nullable = false,unique = true)
    private String mail;

    @Enumerated(EnumType.STRING)
    private UserType userType;



    @ManyToMany(targetEntity = Course.class)
    private List<Course> courses;
    //</editor-fold>


    //<editor-fold desc="Getter , setter , Constructor">


    public int getUserId() {
        return userId;
    }


    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public User(int userId, String firstName, String lastName, String userName, String password, String mail, UserType userType, List<Course> courses) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.mail = mail;
        this.userType = userType;
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", mail='" + mail + '\'' +
                ", userType=" + userType +
                '}';
    }

    public  User()
    {
    }
    //</editor-fold>

    //<editor-fold desc="ENUM">
    private enum UserType{
        TEACHER,
        STUDENT
    }
    //</editor-fold>

}
