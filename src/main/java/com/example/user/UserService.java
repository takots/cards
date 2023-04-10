package com.example.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Integer addUser(UserModel user){
        UserModel newUser = userRepository.save(user);
        return newUser.getId();
    }
}
