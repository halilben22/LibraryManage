package com.halildev.elibrary.repository;

import com.halildev.elibrary.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> getUserById(Long id);




    User findByUsername(String username);

    Optional<User> getUserByUsername( String username);
}
