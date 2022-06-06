package com.startup.MoodleEl8laba.repository;

import com.startup.MoodleEl8laba.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {

   User findByUserName(String userName);

   User findByMail(String mail);
}
