package com.halildev.elibrary.controllers;


import com.halildev.elibrary.dto.requests.CreateUserReq;
import com.halildev.elibrary.models.BookDetails;
import com.halildev.elibrary.models.OwnedBooks;
import com.halildev.elibrary.models.User;
import com.halildev.elibrary.service.JwtService;
import com.halildev.elibrary.service.OwnedBooksService;
import com.halildev.elibrary.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/users")
public class UserController {


    private final UserService userService;
    private final OwnedBooksService ownedBooksService;
    private final JwtService jwtService;

    public UserController(UserService userService, OwnedBooksService ownedBooksService, JwtService jwtService) {
        this.userService = userService;
        this.ownedBooksService = ownedBooksService;
        this.jwtService = jwtService;
    }


    @GetMapping("/all")
    public List<User> getAllUsers() {

        return userService.getAllUsers();
    }


    @GetMapping("/{user_id}")
    public Optional<User> getUser(@PathVariable Long user_id) {

        return userService.getOneUser(user_id);

    }

    @GetMapping("/login")
    public String loginScreen() {
        return "login-screen";
    }

    @GetMapping("/login-auth")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        Optional<User> user = userService.getOneUserByUsername(username);

        List<BookDetails> books = ownedBooksService.getOwnedBookByUserId(user.get().getId());
        if (user.get().getPassword().equals(password)) {

            Set<BookDetails> uniqueBookSet = new HashSet<>(books);
            model.addAttribute("books", uniqueBookSet);

            if (ownedBooksService.getAllOwnedBookList().isEmpty()){
                return "basarisiz-giris";
            }
            return "user-screen";

        }

        return "basarisiz-giris";
    }


    @GetMapping("/register")
    public String redirectRegisterScreen() {

        return "register-screen";
    }

    @GetMapping("/register-user")
    public String registerUser(@ModelAttribute CreateUserReq user) {

        userService.createUser(user);
        return "login-screen";
    }

    @PutMapping("/{user_id}")
    public User updateOneUser(@PathVariable Long user_id, @RequestBody User newUser) {
        return userService.updateOneUser(user_id, newUser);
    }


    @DeleteMapping("/{user_id}")
    public void deleteUser(@PathVariable Long user_id) {
        userService.deleteOneUser(user_id);
    }


}
