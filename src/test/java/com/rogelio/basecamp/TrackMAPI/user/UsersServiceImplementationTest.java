package com.rogelio.basecamp.TrackMAPI.user;

import com.rogelio.basecamp.TrackMAPI.errorhandling.BadRequestException;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
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
@AutoConfigureMockMvc
class UsersServiceImplementationTest {

    @Autowired
    private UsersService usersService;

    @MockBean
    private UsersRepository usersRepository;

    @Test
    @DisplayName("save user success")
    void createUser() {
        //Mocked movie and repository
        User mockUser = new User("DarkRecon", "password", "darkRecon@gmail.com");
        Mockito.when(usersRepository.save(mockUser)).thenReturn(mockUser);

        //Service
        User returnedUser = usersService.createUser(mockUser);

        //Testing
        Assertions.assertNotNull(returnedUser, "Not null");
        Assertions.assertSame(returnedUser, mockUser, "same");
        Assertions.assertEquals(returnedUser.getUsername(), "DarkRecon");
    }

    @Test
    @DisplayName("findAll users success")
    void getAllUsers() {
        //Mocking retrieving collection of movies from repository
        User mockUser1 = new User("DarkRecon", "password", "darkRecon@gmail.com");
        User mockUser2 = new User("sergioLeone", "password", "sergio.leone@gmail.com");
        User mockUser3 = new User("terminator123", "12345", "terminater@gmail.com");
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
        Assertions.assertEquals(returnedUserCollection.get(1).getUsername(), "sergioLeone");
    }

    @Test
    @DisplayName("findById user success")
    void getUser() {
        //Mocking retrieving a movie from repository and assuming it's found
        User mockUser = new User("DarkRecon", "password", "darkRecon@gmail.com");
        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        mockUser.setUserId(objectId);
        Mockito.when(usersRepository.findById(objectId)).thenReturn(Optional.of(mockUser));

        //Service
        User returnedUser = usersService.getUser(mockUser.getUserId());

        Assertions.assertNotNull(returnedUser);
        Assertions.assertSame(returnedUser, mockUser);
        Assertions.assertEquals(returnedUser.getUsername(), "DarkRecon");
    }

    @Test()
    @DisplayName("get user invalid id exception success")
    void getUserInvalidId(){
        //Mocking invalid id throwing exception
        BadRequestException badRequestException = assertThrows(BadRequestException.class, () -> {
            usersService.getUser("123");
        });

        assertEquals("Invalid Id: 123", badRequestException.getMessage());
    }

    @Test
    @DisplayName("update user invalid id exception success")
    void updateUserInvalidId() {
        BadRequestException badRequestException = assertThrows(BadRequestException.class, () -> {
            usersService.updateUser("123", new User("Test", "Test", "Test"));
        });

        assertEquals("Invalid Id: 123", badRequestException.getMessage());
    }

    @Test
    @DisplayName("update user success")
    void updateUserValidIdButFound(){
        User mockUser = new User("DarkRecon", "password", "darkRecon@gmail.com");
        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        mockUser.setUserId(objectId);
        mockUser.setUsername("Test");
        Mockito.when(usersRepository.save(mockUser)).thenReturn(mockUser);
        Mockito.when(usersRepository.existsById(objectId)).thenReturn(true);

        User returnedUser = usersService.updateUser(objectId.toHexString(), mockUser);

        Assertions.assertSame(returnedUser, mockUser);
    }

    @Test
    @DisplayName("delete user invalid id exception success")
    void deleteUserInvalidId() {
        BadRequestException badRequestException = assertThrows(BadRequestException.class, () -> {
            usersService.deleteUser("123");
        });

        assertEquals("Invalid Id: 123", badRequestException.getMessage());
    }

    @Test
    @DisplayName("delete user success")
    void deleteUserValidId() {
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