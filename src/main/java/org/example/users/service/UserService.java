package org.example.users.service;


import org.example.users.model.User;
import org.example.users.repository.UserRepository;
import org.example.users.util.EncryptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        user.setToken(EncryptionUtil.encrypt(user.getUser_name()+":"+user.getUser_password()));
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(int id) {
        return userRepository.getReferenceById((long) id);
    }

    public User getUserLogin(String token) {
        String decodedString = EncryptionUtil.decrypt(token);
        String[] user_pass = decodedString.split(":");
        User user = userRepository.getUserByPassAndName(user_pass[0], EncryptionUtil.encrypt(user_pass[1]));
        return user;
    }
}
