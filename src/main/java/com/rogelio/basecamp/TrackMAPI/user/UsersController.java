package com.rogelio.basecamp.TrackMAPI.user;

import com.rogelio.basecamp.TrackMAPI.errorhandling.BadRequestException;
import com.rogelio.basecamp.TrackMAPI.movie.Movie;
import org.bson.types.ObjectId;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.JsonWebToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
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
    public ResponseEntity<List<User>> getAllUsers(HttpServletRequest request){
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
}
