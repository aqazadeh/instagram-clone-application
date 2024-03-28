package com.aqazadeh.intagramclone.repository;

import com.aqazadeh.intagramclone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsernameAndEmail(String username, String email);

    Optional<User> findByUsername(String username);
}