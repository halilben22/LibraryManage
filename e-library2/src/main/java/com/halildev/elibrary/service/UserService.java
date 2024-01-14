package com.halildev.elibrary.service;


import com.halildev.elibrary.dto.requests.CreateUserReq;
import com.halildev.elibrary.models.User;
import com.halildev.elibrary.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {


   private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<User> getAllUsers() {

        return userRepository.findAll();
    }

    public Optional<User> getOneUser(Long id) {

        return userRepository.getUserById(id);


    }

    public Optional<User> getOneUserByUsername(String username) {

        return userRepository.getUserByUsername(username);


    }

    public User createUser(CreateUserReq req) {
        User newUser = User.builder()
                .firstname(req.getFirstname())
                .lastname(req.getLastname())
                .username(req.getUsername())
                .password(req.getPassword())
                .role(req.getRole())
                .build();
        return userRepository.save(newUser);
    }

    public User updateOneUser(Long id, User newUser) {
        Optional<User> user = userRepository.getUserById(id);


        if (user.isPresent()) {

            User foundUser = user.get();


            foundUser.setUsername(newUser.getUsername());
            foundUser.setPassword(newUser.getPassword());

            foundUser.setOwnedBooksList(newUser.getOwnedBooksList());


            return userRepository.save(foundUser);
        }

        return null;
    }


    public void deleteOneUser(Long id) {

        Optional<User> foundUser = userRepository.getUserById(id);


        foundUser.ifPresent(userRepository::delete);


    }


    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }
}
