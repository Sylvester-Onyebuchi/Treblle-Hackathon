package com.sylvester.trebllehackathon.service;

import com.sylvester.trebllehackathon.dto.CreateUserRequest;
import com.sylvester.trebllehackathon.dto.Response;
import com.sylvester.trebllehackathon.entity.User;
import com.sylvester.trebllehackathon.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    Response createUser(CreateUserRequest createUserRequest);

    User getUserById(Long id);

    List<User> getAllUsers();

    void deleteUser(Long id);

    Response updateUser(Long id, CreateUserRequest createUserRequest);



}
