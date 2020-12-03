package com.rogelio.basecamp.TrackMAPI.user;

import com.rogelio.basecamp.TrackMAPI.errorhandling.BadRequestException;
import com.rogelio.basecamp.TrackMAPI.errorhandling.RecordNotFoundException;
import org.bson.codecs.pojo.annotations.BsonDiscriminator;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc()
class UsersServiceImplementationTest {

    @Autowired
    private UsersService usersService;

    @MockBean
    private UsersRepository usersRepository;

    @Test
    @DisplayName("save user success")
    void createUserDoesNotExist() {
        //Mocked movie and repository
        User mockUser = new User();
        mockUser.setUserId("asdsehw334y6u");
        mockUser.setMoviesWatched(0);
        mockUser.setTvSeriesWatched(0);
        mockUser.setGamesPlayed(0);

        Mockito.when(usersRepository.save(mockUser)).thenReturn(mockUser);
        Mockito.when(usersRepository.existsById(mockUser.getUserId())).thenReturn(false);

        //Service
        User returnedUser = usersService.createUser(mockUser);

        //Testing
        Assertions.assertNotNull(returnedUser, "Not null");
        Assertions.assertSame(returnedUser, mockUser, "same");
    }

    @Test
    @DisplayName("user exists updates existing user")
    void createUserExist(){
        User mockUser = new User();
        mockUser.setUserId("asdsehw334y6u");
        mockUser.setMoviesWatched(0);
        mockUser.setTvSeriesWatched(0);
        mockUser.setGamesPlayed(0);

        Mockito.when(usersRepository.save(mockUser)).thenReturn(mockUser);
        Mockito.when(usersRepository.existsById(mockUser.getUserId())).thenReturn(true);
        Mockito.when(usersRepository.findById(mockUser.getUserId())).thenReturn(Optional.of(mockUser));

        User returnedUser = usersService.createUser(mockUser);

        Assertions.assertNotNull(returnedUser, "Not null");
        Assertions.assertSame(returnedUser, mockUser, "same");

    }

    @Test
    @DisplayName("findAll users success")
    void getAllUsers() {
        //Mocking retrieving collection of movies from repository
        User mockUser1 = new User();
        mockUser1.setUserId("asdsehw334y6u");
        mockUser1.setMoviesWatched(0);
        mockUser1.setTvSeriesWatched(0);
        mockUser1.setGamesPlayed(0);

        User mockUser2 = new User();
        mockUser1.setUserId("asdasdasdfgdfdh4536");
        mockUser1.setMoviesWatched(0);
        mockUser1.setTvSeriesWatched(0);
        mockUser1.setGamesPlayed(0);

        User mockUser3 = new User();
        mockUser1.setUserId("asasfsdgdfghdrjui56yr2e12e12");
        mockUser1.setMoviesWatched(0);
        mockUser1.setTvSeriesWatched(0);
        mockUser1.setGamesPlayed(0);

        List<User> mockUserCollection = new ArrayList<User>();
        mockUserCollection.add(mockUser1);
        mockUserCollection.add(mockUser2);
        mockUserCollection.add(mockUser3);

        Mockito.when(usersRepository.findAll()).thenReturn(mockUserCollection);

        //Service
        List<User> returnedUserCollection = usersService.getAllUsers();

        //Validate
        Assertions.assertFalse(returnedUserCollection.isEmpty());
        Assertions.assertSame(returnedUserCollection.get(0), mockUser1);
    }

    @Test
    @DisplayName("findById user success")
    void getUser() {
        //Mocking retrieving a movie from repository and assuming it's found
        String userId = "5f91658ec735df31bb0cf2dc";
        User mockUser = new User();
        mockUser.setUserId(userId);

        Mockito.when(usersRepository.findById(userId)).thenReturn(Optional.of(mockUser));

        //Service
        User returnedUser = usersService.getUser(mockUser.getUserId());

        Assertions.assertNotNull(returnedUser);
        Assertions.assertSame(returnedUser, mockUser);
    }

    @Test
    @DisplayName("findById user not found")
    void getUserNotFound() {
        //Mocking retrieving a movie from repository and assuming it's found
        String userId = "5f91658ec735df31bb0cf2dc";
        User mockUser = new User();
        mockUser.setUserId(userId);

        Mockito.when(usersRepository.findById(userId)).thenReturn(Optional.empty());

        RecordNotFoundException notFoundException = assertThrows(RecordNotFoundException.class, () -> {
            usersService.getUser(userId);
        });

        assertEquals("Can't find " + userId + ". It does not exist", notFoundException.getMessage());

        //Service
        //User returnedUser = usersService.getUser(mockUser.getUserId());

        //Assertions.assertNotNull(returnedUser);
        //Assertions.assertSame(returnedUser, mockUser);
    }

    @Test
    @DisplayName("update user success")
    void updateUser(){
        String userId = "5f91658ec735df31bb0cf2dc";
        User mockUser = new User();
        mockUser.setUserId(userId);
        mockUser.setGamesPlayed(0);
        mockUser.setGamesPlayed(0);
        mockUser.setGamesPlayed(0);

        Mockito.when(usersRepository.save(mockUser)).thenReturn(mockUser);
        //Mockito.when(usersRepository.existsById(userId)).thenReturn(true);
        Mockito.when(usersRepository.findById(userId)).thenReturn(Optional.of(mockUser));

        User returnedUser = usersService.updateUser(userId, mockUser);

        Assertions.assertNotNull(returnedUser);
        Assertions.assertSame(returnedUser, mockUser);
    }

    @Test
    @DisplayName("delete user success")
    void deleteUser() {
        usersService.deleteUser("5f91658ec735df31bb0cf2dc");
        assertEquals(usersService.deleteUser("5f91658ec735df31bb0cf2dc"), "Successfully deleted user");
    }


    @Test
    @DisplayName("delete all users")
    void deleteAllUsers() {
        usersService.deleteAllUsers();
        assertEquals(usersService.deleteAllUsers(), "Successfully deleted all users");
    }

}