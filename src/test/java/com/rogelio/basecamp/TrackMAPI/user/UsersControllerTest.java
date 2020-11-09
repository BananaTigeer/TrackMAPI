package com.rogelio.basecamp.TrackMAPI.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rogelio.basecamp.TrackMAPI.movie.Movie;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UsersControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsersService usersService;

    @Test
    @DisplayName("POST user success")
    void createUser() throws Exception {
        // Sets new User to add then mock it to see if it works?
        User mockUser = new User("Spartan117", "1234", "email@email.com");
        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        mockUser.setUserId(objectId);

        Mockito.when(usersService.createUser(ArgumentMatchers.any())).thenReturn(mockUser);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/users")
                .content(asJsonString(mockUser))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(jsonPath("$.userId", is("5f91658ec735df31bb0cf2dc")));
    }

    @Test
    @DisplayName("GET all users success")
    void getAllUsers() throws Exception{
        User user1 = new User("Spartan117", "1234", "email@email.com");
        User user2 = new User("ThornedPrism", "1234", "thornedprism@gmail.com");
        User user3 = new User("Spartan1337", "1337", "spartan@live.com");

        ObjectId objectId1 = new ObjectId("5f91658ec735df31bb0cf2da");
        ObjectId objectId2 = new ObjectId("5f91658ec735df31bb0cf2db");
        ObjectId objectId3 = new ObjectId("5f91658ec735df31bb0cf2dc");

        user1.setUserId(objectId1);
        user2.setUserId(objectId2);
        user3.setUserId(objectId3);

        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);

        doReturn(users).when(usersService).getAllUsers();

        mockMvc.perform(MockMvcRequestBuilders
                .get("/users"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].userId", is("5f91658ec735df31bb0cf2da")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].username", is("Spartan117")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].password", is("1234")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].email", is("email@email.com")));
    }

    @Test
    @DisplayName("GET user success")
    void getUser() throws Exception{
        User mockUser = new User("Spartan117", "1234", "email@email.com");
        ObjectId objectId1 = new ObjectId("5f91658ec735df31bb0cf2dc");
        mockUser.setUserId(objectId1);

        doReturn(mockUser).when(usersService).getUser(mockUser.getUserId());

        mockMvc.perform(MockMvcRequestBuilders
                .get("/users/{userId}",objectId1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.username", is("Spartan117")));
    }

    @Test
    @DisplayName("PUT user success")
    void putUser() throws Exception {
        User mockUser = new User("Spartan117", "1234", "email@email.com");

        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        mockUser.setUserId(objectId);
        mockUser.setUsername("Test");
        mockUser.setPassword("");
        mockUser.setEmail("");

        //doReturn(mockMovie).when(moviesService).updateMovie(objectId, mockMovie);
        Mockito.when(usersService.updateUser(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(mockUser);

        mockMvc.perform(MockMvcRequestBuilders
                .put("/users/{userId}", objectId.toHexString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(mockUser))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.username", is("Test")))
                .andExpect(jsonPath("$.password", is("")));
    }

    @Test
    @DisplayName("PATCH user success")
    void patchUser() throws Exception {
        User mockUser = new User("Spartan117", "1234", "email@email.com");
        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        mockUser.setUserId(objectId);
        mockUser.setUsername("Test");

        Mockito.when(usersService.updateUser(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(mockUser);

        mockMvc.perform(MockMvcRequestBuilders
                .patch("/users/{userId}", objectId.toHexString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(mockUser))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.username", is("Test")))
                .andExpect(jsonPath("$.password", is("1234")));
    }

    @Test
    @DisplayName("DELETE user success")
    void deleteUser() throws Exception{
        ObjectId objectid = new ObjectId("5f91658ec735df31bb0cf2dc");
        Mockito.when(usersService.deleteUser(ArgumentMatchers.any())).thenReturn("Successfully deleted user");

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/users/{usersId}", objectid.toHexString()))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Successfully deleted user"));
    }
    @Test
    @DisplayName("DELETE all users success")
    void deleteAllMovies() throws Exception{
        Mockito.when(usersService.deleteAllUsers()).thenReturn("Successfully deleted all users");

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/users"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Successfully deleted all users"));
    }

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}