package com.sylvester.trebllehackathon.controller;

import com.sylvester.trebllehackathon.dto.CreateUserRequest;
import com.sylvester.trebllehackathon.dto.LoginRequest;
import com.sylvester.trebllehackathon.dto.Response;
import com.sylvester.trebllehackathon.entity.User;
import com.sylvester.trebllehackathon.jwt.JwtService;
import com.sylvester.trebllehackathon.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User API", description = "User related APIs")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Operation(summary = "Save a new user to the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Saved the user details"),
            @ApiResponse(responseCode = "400", description = "User already exist"),
            @ApiResponse(responseCode = "500", description = "Something went wrong")
    })
    @PostMapping("/user/new-user")
    public ResponseEntity<Response> newUser(@RequestBody CreateUserRequest request) {
        Response response = userService.createUser(request);
        return ResponseEntity.ok(response);
    }


    @Operation(summary = "Get a user by user id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Saved the user details"),
            @ApiResponse(responseCode = "404", description = "User not found"),

    })
    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable long id) {
        return ResponseEntity.ok(userService.getUserById(id));

    }


    @Operation(summary = "Get all the users")
    @ApiResponse(responseCode = "200", description = "List all the users")
    @GetMapping("/all-users")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @Operation(summary = "Update user information using userId")
    @PutMapping("/user/update/{id}")
    public ResponseEntity<Response> updateUser(@PathVariable long id, @RequestBody CreateUserRequest request) {
        Response response = userService.updateUser(id, request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Login as a  user")
    @PostMapping("/user/login")
    public ResponseEntity<Map<String,String>> loginUser(@RequestBody LoginRequest request){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        if(authentication.isAuthenticated()){
            String jwt = jwtService.generateToken(request.getEmail());

            ResponseCookie cookie = ResponseCookie.from("jwtToken", jwt)
                    .httpOnly(true)
                    .secure(true)
                    .path("/")
                    .maxAge(60 * 60 * 12)
                    .sameSite("Strict")
                    .build();

            return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                    .body(Map.of("Login Successful",jwt));
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();


    }



    @Operation(summary = "Logout as a  user")
    @PostMapping("/user/logout")
    public ResponseEntity<?> logoutUser(){
        ResponseCookie cookie = ResponseCookie.from("jwtToken", "")
                .httpOnly(true)
                .secure(true)
                .path("/")
                .sameSite("Strict")
                .maxAge(0)
                .build();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(Map.of("message","Logout successful!"));
    }


    @Operation(summary = "Delete a user using user id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted the user"),
            @ApiResponse(responseCode = "404", description = "User not found"),

    })
    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
