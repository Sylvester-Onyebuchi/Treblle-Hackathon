package com.sylvester.trebllehackathon.repository;

import com.sylvester.trebllehackathon.dto.Response;
import com.sylvester.trebllehackathon.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

}
