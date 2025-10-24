package com.sylvester.trebllehackathon.serviceImplementation;
import com.sylvester.trebllehackathon.dto.CreateUserRequest;
import com.sylvester.trebllehackathon.dto.Response;
import com.sylvester.trebllehackathon.entity.User;
import com.sylvester.trebllehackathon.exception.UserAlreadyExistException;
import com.sylvester.trebllehackathon.exception.UserNotFoundException;
import com.sylvester.trebllehackathon.repository.UserRepository;
import com.sylvester.trebllehackathon.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public Response createUser(CreateUserRequest request) {

        Optional<User> existingUser = userRepository.findByEmail(request.getEmail());
        if (existingUser.isPresent()) {
            throw new UserAlreadyExistException("User already exists");
        }


            User newUser = User.builder()
                    .name(request.getName())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .build();
            User savedUser = userRepository.save(newUser);
            return Response.builder()
                    .message("User has been created successfully!")
                    .name(savedUser.getName())
                    .email(savedUser.getEmail())
                    .build();




    }


    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User not found")
        );

    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User not found")
        );
        userRepository.deleteById(id);
    }

    @Override
    public Response updateUser(Long id, CreateUserRequest createUserRequest) {
        userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User not found")
        );
        User user = User.builder()
                .name(createUserRequest.getName())
                .email(createUserRequest.getEmail())
                .build();
        User savedUser = userRepository.save(user);

        return Response.builder()
                .message("User has been updated successfully!")
                .name(savedUser.getName())
                .email(savedUser.getEmail())
                .build();
    }
}
