package com.sylvester.trebllehackathon.serviceImplementation;

import com.sylvester.trebllehackathon.dto.CreateUserRequest;
import com.sylvester.trebllehackathon.dto.Response;
import com.sylvester.trebllehackathon.entity.User;
import com.sylvester.trebllehackathon.exception.UserAlreadyExistException;
import com.sylvester.trebllehackathon.exception.UserNotFoundException;
import com.sylvester.trebllehackathon.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class UserServiceImplementationTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImplementation userService;

    private CreateUserRequest request;
    private User savedUser;
    private final Long TEST_USERID = 1L;

    @BeforeEach
    void setUp() {
        request = new CreateUserRequest("Alice Smith", "alice.smith@example.com");
        savedUser = User.builder()
                .id(TEST_USERID)
                .name("Alice Smith")
                .email("alice.smith@example.com")
                .build();
    }

    @Test
    void createUserIfEmailNotExistsTest() {
        when(userRepository.findByEmail(request.getEmail()))
                .thenReturn(Optional.empty());
        when(userRepository.save(any(User.class)))
                .thenReturn(savedUser);


        Response response = userService.createUser(request);

        assertNotNull(response);
        assertEquals("User has been created successfully!", response.getMessage());
        assertEquals(savedUser.getName(), response.getName());
        assertEquals(savedUser.getEmail(), response.getEmail());

        verify(userRepository, times(1)).findByEmail(request.getEmail());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void createUserIfEmailExistsTest() {
        User existingUser = new User("User", request.getEmail());

        when(userRepository.findByEmail(request.getEmail()))
                .thenReturn(Optional.of(existingUser));

        UserAlreadyExistException exception = assertThrows(UserAlreadyExistException.class, () -> {
            userService.createUser(request);
        });

        assertEquals("User already exists", exception.getMessage());
        verify(userRepository, times(1)).findByEmail(request.getEmail());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void getAllUsersTest() {
        when(userRepository.findAll()).thenReturn(List.of(savedUser));
        assertEquals(List.of(savedUser), userService.getAllUsers());
    }

    @Test
    void getUserByIdTest() {
        when(userRepository.findById(TEST_USERID))
                .thenReturn(Optional.of(savedUser));

        User found = userService.getUserById(TEST_USERID);
        assertEquals(savedUser, found);

        verify(userRepository, times(1)).findById(TEST_USERID);
    }

    @Test
    void getUserByIdNotFoundTest() {
        when(userRepository.findById(TEST_USERID)).thenReturn(Optional.empty());
        UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> {
            userService.getUserById(TEST_USERID);
        });
        assertEquals("User not found", exception.getMessage());
        verify(userRepository, times(1)).findById(TEST_USERID);
    }
    @Test
    void updateUserTest() {
        when(userRepository.findById(TEST_USERID))
        .thenReturn(Optional.of(savedUser));
        when(userRepository.save(any(User.class)))
        .thenReturn(savedUser);

        Response response = userService.updateUser(TEST_USERID, request);
        assertNotNull(response);
        assertEquals("User has been updated successfully!", response.getMessage());
        assertEquals(savedUser.getName(), response.getName());
        assertEquals(savedUser.getEmail(), response.getEmail());
        verify(userRepository, times(1)).findById(TEST_USERID);
        verify(userRepository, times(1)).save(any(User.class));

    }

    @Test
    void updateUserNotFoundTest() {
        when(userRepository.findById(TEST_USERID)).thenReturn(Optional.empty());
        UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> {
            userService.updateUser(TEST_USERID, request);
        });
        assertEquals("User not found", exception.getMessage());
        verify(userRepository, times(1)).findById(TEST_USERID);
        verify(userRepository, never()).save(any(User.class));

    }

    @Test
    void deleteUserTest() {
        when(userRepository.findById(TEST_USERID))
        .thenReturn(Optional.of(savedUser));
        doNothing().when(userRepository).deleteById(TEST_USERID);
       assertDoesNotThrow(() -> {
           userService.deleteUser(TEST_USERID);
       });
        verify(userRepository, times(1)).findById(TEST_USERID);

        verify(userRepository, times(1)).deleteById(TEST_USERID);
    }

    @Test
    void deleteUserNotFoundTest() {
        when(userRepository.findById(TEST_USERID)).thenReturn(Optional.empty());
        UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> {
            userService.deleteUser(TEST_USERID);
        });
        assertEquals("User not found", exception.getMessage());
        verify(userRepository, never()).deleteById(TEST_USERID);

    }



}