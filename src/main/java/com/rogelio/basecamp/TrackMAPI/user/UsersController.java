package com.rogelio.basecamp.TrackMAPI.user;

import com.rogelio.basecamp.TrackMAPI.errorhandling.BadRequestException;
import com.rogelio.basecamp.TrackMAPI.movie.Movie;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @PostMapping("")
    public ResponseEntity<User> createUser(@RequestBody User user ){
        User createdUser = usersService.createUser(user);

        try{
            return ResponseEntity.created(new URI("/users"))
                    .body(createdUser);
        }catch(URISyntaxException e){
            throw new RuntimeException("Error in POST /movies");
        }
    }

    @GetMapping("")
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok().body(usersService.getAllUsers());
    }

    @GetMapping("/{userId}")
    public ResponseEntity getUser(@Valid @PathVariable String userId){
        return ResponseEntity.ok().body(usersService.getUser(userId));
    }

    @PutMapping("/{userId}")
    public ResponseEntity putUser(@Valid @PathVariable String userId, @RequestBody User user){
        return ResponseEntity.ok().body(usersService.updateUser(userId, user));
    }

    @PatchMapping("/{userId}")
    public ResponseEntity patchUser(@Valid @PathVariable String userId, @RequestBody User user){
        return ResponseEntity.ok().body(usersService.updateUser(userId, user));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity deleteUser(@Valid @PathVariable String userId){
        return ResponseEntity.ok().body(usersService.deleteUser(userId));
    }

    @DeleteMapping("")
    public ResponseEntity deleteAllMovies(){
        return ResponseEntity.ok().body(usersService.deleteAllUsers());
    }

/*    @PostMapping("")
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
    }*/

}
