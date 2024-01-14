package com.halildev.elibrary.controllers;


import com.halildev.elibrary.dto.requests.CreateOwnedBookRequest;
import com.halildev.elibrary.dto.requests.UpdateOwnedBookRequest;
import com.halildev.elibrary.models.BookDetails;
import com.halildev.elibrary.models.OwnedBooks;
import com.halildev.elibrary.models.User;
import com.halildev.elibrary.service.BooksDetailsService;
import com.halildev.elibrary.service.OwnedBooksService;
import com.halildev.elibrary.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.*;

@Controller
@RequestMapping("/owned")
public class OwnedBookController {


    private final OwnedBooksService ownedBooksService;

    private final UserService userService;
    private final BooksDetailsService bookService;

    public OwnedBookController(OwnedBooksService ownedBooksService, UserService userService, BooksDetailsService bookService) {
        this.ownedBooksService = ownedBooksService;
        this.userService = userService;
        this.bookService = bookService;
    }

    @GetMapping("/all/{id}")
    public List<BookDetails> getAllOwnedBooks(@PathVariable Long id) {

        return ownedBooksService.getOwnedBookByUserId(id);
    }

    @GetMapping("/{ownedbook_id}")
    public OwnedBooks getOneOwnedBook(@PathVariable Long ownedbook_id) {
        return ownedBooksService.getOneOwnedBook(ownedbook_id);
    }

    @PostMapping("/owned-book-page")
    public String redirectOwnedPage() {


        return "redirect:/owned/ownbook";
    }


    @GetMapping("/open-borrow-page/{bookName}")
    public String borrowPage(@PathVariable String bookName, Model model) {
        Optional<BookDetails> book = bookService.getOneBookDetailByName(bookName);
       BookDetails bookAt=book.get();

        model.addAttribute("book", bookAt);
        return "borrow-page";
    }


    @PostMapping("/ownbook")
    public String createOneOwnedBook(@ModelAttribute User request, @ModelAttribute BookDetails requestBook, Model model) {


        Optional<User> user = userService.getOneUserByUsername(request.getUsername());
        Optional<BookDetails> book = bookService.getOneBookDetailByName(requestBook.getBookName());
        OwnedBooks request1 = OwnedBooks.builder()
                .user(user.get())
                .bookDetails(book.get())
                .build();
        ownedBooksService.createOwnedBook(request1);

        List<BookDetails> books = ownedBooksService.getOwnedBookByUserId(user.get().getId());


        Set<BookDetails> uniqueBookSet = new HashSet<>(books);
        model.addAttribute("books", uniqueBookSet);


        return "user-screen";

    }


    @PutMapping("/{ownedbook_id}")
    public OwnedBooks updateOneOwnedBook(@RequestBody UpdateOwnedBookRequest updateOwnedBookRequest, @PathVariable Long ownedbook_id) {


        return ownedBooksService.updateOneOwnedBook(updateOwnedBookRequest, ownedbook_id);
    }

    @PostMapping("delete/{ownedbook_id}")

    public String deleteOneOwnedBook(@PathVariable Long ownedbook_id) {

        ownedBooksService.deleteOneOwnedBook(ownedbook_id);
        return "user-screen";
    }
}
