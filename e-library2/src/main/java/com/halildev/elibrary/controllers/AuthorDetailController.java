package com.halildev.elibrary.controllers;


import com.halildev.elibrary.dto.requests.CreateAuthorRequest;
import com.halildev.elibrary.dto.requests.UpdateAuthorRequest;
import com.halildev.elibrary.models.AuthorDetails;
import com.halildev.elibrary.models.BookDetails;
import com.halildev.elibrary.service.AuthorDetailsService;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/details")
public class AuthorDetailController {


    private final AuthorDetailsService authorDetailsService;

    public AuthorDetailController(AuthorDetailsService authorDetailsService) {
        this.authorDetailsService = authorDetailsService;
    }

    @GetMapping
    public List<AuthorDetails> getAllAuthors() {

        return authorDetailsService.getAllAuthors();
    }

    @GetMapping("/detail_book/{id}")

    public String getDetailsOfBook(@PathVariable Long id, Model model){
        BookDetails book=authorDetailsService.getBooksByAuthors(id).get(0).getBookDetails();

        List<AuthorDetails>authorDetails=authorDetailsService.getBooksByAuthors(id);

        model.addAttribute("authors",authorDetails);
        model.addAttribute("book",book);
        return "book-details";
    }


    @GetMapping("{author_id}")

    public AuthorDetails getOneAuthorById(@PathVariable Long author_id) throws Exception {

        return authorDetailsService.getOneAuthor(author_id).orElseThrow(Exception::new);
    }


    @PostMapping()
    public AuthorDetails createOneAuthor(@RequestBody CreateAuthorRequest authorRequest) {


        return authorDetailsService.createAuthor(authorRequest);

    }


    @PutMapping("/{author_id}")

    public AuthorDetails updateOneAuthor(@PathVariable Long author_id, @RequestBody UpdateAuthorRequest authorRequest) {
        return authorDetailsService.updateOneAuthor(author_id, authorRequest);
    }


    @DeleteMapping("/{author_id}")

    public void deleteOneAuthor(@PathVariable Long author_id) {

        authorDetailsService.deleteOneAuthor(author_id);
    }


}
