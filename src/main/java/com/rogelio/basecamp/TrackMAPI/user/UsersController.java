package com.rogelio.basecamp.TrackMAPI.user;

import com.rogelio.basecamp.TrackMAPI.errorhandling.BadRequestException;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
            throw new BadRequestException("Invalid Id: " + userId);
        }

        ObjectId objectId = new ObjectId(userId);
        return usersService.getUser(objectId);
    }

    @PutMapping("/{userId}")
    public User putUser(@PathVariable String userId, @RequestBody User user){
        if(!ObjectId.isValid(userId)){
            throw new BadRequestException("Invalid Id: " + userId);
        }

        ObjectId objectId = new ObjectId(userId);

        return usersService.updateUser(objectId, user);
    }

    @PatchMapping("/{userId}")
    public User patchUser(@PathVariable ObjectId userId, @RequestBody User user){
        return usersService.updateUser(userId, user);
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
