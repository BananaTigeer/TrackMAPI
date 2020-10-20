package com.rogelio.basecamp.TrackMAPI.Controllers;

import com.rogelio.basecamp.TrackMAPI.Models.User;
import com.rogelio.basecamp.TrackMAPI.Service.UsersServiceImplementation;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersServiceImplementation usersServiceImplementation;

    @PostMapping("")
    public void createUser(@RequestBody User user){
        usersServiceImplementation.createUser(user);
    }

    @GetMapping("")
    public List<User> getAllUsers(){
        return usersServiceImplementation.getAllUsers();
    }

    @GetMapping("/{userId}")
    public Optional<User> getUser(@PathVariable ObjectId userId){
        return usersServiceImplementation.getUser(userId);
    }

    @PutMapping("/{userId}")
    public User putUser(@PathVariable ObjectId userId, @RequestBody User user){
        return usersServiceImplementation.putUser(userId, user);
    }

    @PatchMapping("/{userId}")
    public User patchUser(@PathVariable ObjectId userId, @RequestBody User user){
        return usersServiceImplementation.patchUser(userId, user);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable ObjectId userId){
        usersServiceImplementation.deleteUser(userId);
    }

    @DeleteMapping("")
    public void deleteAllUsers(){
        usersServiceImplementation.deleteAllUsers();
    }

}
