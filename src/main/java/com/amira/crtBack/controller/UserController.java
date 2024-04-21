package com.amira.crtBack.controller;

import com.amira.crtBack.dto.User;
import com.amira.crtBack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    //http://localhost:8080/user/get-all-users
    @GetMapping("/get-all-users")
    @ResponseBody
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    //http://localhost:8080/user/get-user-by-id/2
    @GetMapping("/get-user-by-id/{userId}")
    @ResponseBody
    public User getUserById(@PathVariable("userId") Long userId) {
        return userService.getUserById(userId);
    }

    //http://localhost:8080/user/add-user
    @PostMapping("/add-user")
    @ResponseBody
    public User addUser(@RequestBody User user) {
        User userCreated = userService.addUser(user);
        return userCreated;
    }

    //http://localhost:8080/user/delete-user/2
    @DeleteMapping("/delete-user/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable("id") Long id) {
    //public void deleteUserById(@PathVariable("id") Long id) {

        userService.deleteUserByEmail(id);
        return ResponseEntity.ok().build();
    }

    //http://localhost:8080/user/update-user/4
    @PutMapping("/update-user/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        try {
            User userUpdated = userService.updateUser(id, user);
            return ResponseEntity.ok(userUpdated); // Retourne un statut 200 OK avec l'utilisateur mis à jour
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id not found, No update !!.");
        }
    }

    //http://localhost:8080/user/get-userPassword/amira1@gmail.com
    @GetMapping("/get-userPassword/{email}")
    @ResponseBody
    public String getUserPassword(@PathVariable("email") String email) {
        return userService.getUserPassword(email);
    }
}
