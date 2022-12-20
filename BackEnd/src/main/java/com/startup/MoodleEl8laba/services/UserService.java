package com.startup.MoodleEl8laba.services;

import com.startup.MoodleEl8laba.models.User;
import com.startup.MoodleEl8laba.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepo;

    @Autowired
    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public User tryLogin(User logUser) throws Exception {
        //System.out.println(logUser.toString());
        //System.out.println(logUser.getUserName());

        User dbUser = this.userRepo.findByUserName(logUser.getUserName());

        if (dbUser != null) {

            System.out.println(dbUser.getUserName());
            if (logUser.getPassword().equals(dbUser.getPassword()))
                return dbUser;
            else
                throw new Exception("The given Password was incorrect");
        } else
            throw new Exception("The given Username is not correct");

        //System.out.println("Log user is null");
    }

    public User tryRegister(User regUser) throws Exception {

        User dbUser = this.userRepo.findByUserName(regUser.getUserName());

        if (dbUser != null)
            throw new Exception("A user with the username " + dbUser.getUserName() + " already exists");

        dbUser = this.userRepo.findByMail(regUser.getMail());
        if (dbUser != null)
            throw new Exception("A user with the E-Mail " + dbUser.getMail() + "already exists");

        return this.userRepo.save(regUser);
    }

    public boolean userNameExists(String userName) {

        User dbUser = this.userRepo.findByUserName(userName);

        if (dbUser == null)
            return false;
        else
            return true;

    }
}
