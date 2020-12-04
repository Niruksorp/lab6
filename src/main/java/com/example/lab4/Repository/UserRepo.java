package com.example.lab4.Repository;

import com.example.lab4.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo  extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
}
