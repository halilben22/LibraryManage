package com.halildev.elibrary.controllers;


import com.halildev.elibrary.dto.requests.CreateBookRequest;
import com.halildev.elibrary.dto.requests.UpdateBookRequest;
import com.halildev.elibrary.models.BookDetails;
import com.halildev.elibrary.models.User;
import com.halildev.elibrary.service.BooksDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookDetailController {

    private final BooksDetailsService bookService;

    public BookDetailController(BooksDetailsService bookService) {
        this.bookService = bookService;
    }


    @GetMapping
    public List<BookDetails> getAllBooks() {

        return bookService.getAllBooks();

    }

    @GetMapping("/books")
    public String bookPage(Model model){
       List <BookDetails >bookDetails=bookService.getAllBooks();
        model.addAttribute("books", bookDetails);
        return "books";
    }


    @GetMapping("/{book_id}")
    public Optional<BookDetails> getBook(@PathVariable Long book_id) {

        return bookService.getOneBookDetail(book_id);

    }


    @PostMapping
    public BookDetails createBook(@RequestBody CreateBookRequest newBook) {


        return bookService.createOneBookDetail(newBook);
    }


    @PutMapping("/{book_id}")
    public BookDetails updateOneBook(@PathVariable Long book_id, @RequestBody UpdateBookRequest newBook) {
        return bookService.updateOneBook(book_id, newBook);
    }

    @DeleteMapping("/{book_id}")
    public void deleteBook(@PathVariable Long book_id) {
        bookService.deleteOneBook(book_id);
    }
}
