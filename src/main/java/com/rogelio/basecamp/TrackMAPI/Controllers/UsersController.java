package com.rogelio.basecamp.TrackMAPI.Controllers;

import com.rogelio.basecamp.TrackMAPI.Models.User;
import com.rogelio.basecamp.TrackMAPI.Service.UsersService;
import com.rogelio.basecamp.TrackMAPI.Service.UsersServiceImplementation;
import com.rogelio.basecamp.TrackMAPI.errorhandlin.BadSyntaxException;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @PostMapping("")
    public void createUser(@RequestBody User user){
        usersService.createUser(user);
    }

    @GetMapping("")
    public List<User> getAllUsers(){
        return usersService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public User getUser(@PathVariable String userId){
        //if hex string is invalid, throws bad syntax exception
        if(!ObjectId.isValid(userId)){
            throw new BadSyntaxException("Invalid Id: " + userId);
        }

        ObjectId objectId = new ObjectId(userId);
        return usersService.getUser(objectId);
    }

    @PutMapping("/{userId}")
    public User putUser(@PathVariable String userId, @RequestBody User user){
        if(!ObjectId.isValid(userId)){
            throw new BadSyntaxException("Invalid Id: " + userId);
        }

        ObjectId objectId = new ObjectId(userId);

        return usersService.putUser(objectId, user);
    }

    @PatchMapping("/{userId}")
    public User patchUser(@PathVariable ObjectId userId, @RequestBody User user){
        return usersService.patchUser(userId, user);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable ObjectId userId){
        usersService.deleteUser(userId);
    }

    @DeleteMapping("")
    public void deleteAllUsers(){
        usersService.deleteAllUsers();
    }

}
