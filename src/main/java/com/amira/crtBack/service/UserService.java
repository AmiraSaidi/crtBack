package com.amira.crtBack.service;

import com.amira.crtBack.dto.User;
import com.amira.crtBack.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public User getUserById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        System.out.println("getUserById result : " + user);
        return user;
    }

    public User addUser(User user) {
        User userCreated = userRepository.save(user);
        return userCreated;
    }

    public void deleteUserByEmail(Long id) {
        userRepository.deleteById(id);
    }

    public User updateUser(Long id, User user) {

        User oldUserFound;

        Optional<User> oldUser = userRepository.findById(id);

        if(oldUser.isPresent()) {
            oldUserFound = oldUser.get();
        } else {
            throw new RuntimeException("id not present in database -> No update to be done !!");
        }

        if(user.getName() != null ) { oldUserFound.setName(user.getName());}
        if(user.getEmail() != null ) { oldUserFound.setEmail(user.getEmail());}
        if(user.getPassword() != null ) { oldUserFound.setPassword(user.getPassword());}
        return userRepository.save(oldUserFound);
    }

    public String getUserPassword(String email) {
        String pwd = userRepository.findPasswordByEmail(email);
        return pwd;
    }
}
