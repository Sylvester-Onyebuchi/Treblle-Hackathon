package com.sylvester.trebllehackathon.controller;

import com.sylvester.trebllehackathon.dto.CreateUserRequest;
import com.sylvester.trebllehackathon.dto.Response;
import com.sylvester.trebllehackathon.entity.User;
import com.sylvester.trebllehackathon.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/user")
@Tag(name = "User API", description = "User related APIs")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @Operation(summary = "Save a new user to the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Saved the user details"),
            @ApiResponse(responseCode = "400", description = "User already exist"),
            @ApiResponse(responseCode = "500", description = "Something went wrong")
    })
    @PostMapping("/new-user")
    public ResponseEntity<Response> newUser(@RequestBody CreateUserRequest request) {
        Response response = userService.createUser(request);
        return ResponseEntity.ok(response);
    }


    @Operation(summary = "Get a user by user id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Saved the user details"),
            @ApiResponse(responseCode = "404", description = "User not found"),

    })
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable long id) {
        return ResponseEntity.ok(userService.getUserById(id));

    }


    @Operation(summary = "Get all the users")
    @ApiResponse(responseCode = "200", description = "List all the users")
    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @Operation(summary = "Update user information using userId")
    @PutMapping("/update/{id}")
    public ResponseEntity<Response> updateUser(@PathVariable long id, @RequestBody CreateUserRequest request) {
        Response response = userService.updateUser(id, request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Delete a user using user id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted the user"),
            @ApiResponse(responseCode = "404", description = "User not found"),

    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
