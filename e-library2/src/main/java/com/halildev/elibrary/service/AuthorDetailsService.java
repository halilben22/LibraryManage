package com.halildev.elibrary.service;


import com.halildev.elibrary.dto.requests.CreateAuthorRequest;
import com.halildev.elibrary.dto.requests.UpdateAuthorRequest;
import com.halildev.elibrary.models.AuthorDetails;
import com.halildev.elibrary.models.BookDetails;

import com.halildev.elibrary.repository.AuthorDetailsRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorDetailsService {


    private final AuthorDetailsRepository authorDetailsRepository;

    private final BooksDetailsService booksDetailsService;


    public AuthorDetailsService(AuthorDetailsRepository authorDetailsRepository, BooksDetailsService booksDetailsService) {
        this.authorDetailsRepository = authorDetailsRepository;

        this.booksDetailsService = booksDetailsService;

    }


    public List<AuthorDetails> getAllAuthors() {
        return authorDetailsRepository.findAll();

    }

    public Optional<AuthorDetails> getOneAuthor(Long id) {


        return authorDetailsRepository.getAuthorById(id);


    }

    public AuthorDetails createAuthor(CreateAuthorRequest authorRequest) {


        BookDetails bookDetails = booksDetailsService.getOneBookDetail(authorRequest.getBook_id()).orElse(null);
        if (bookDetails == null) {
            return null;
        }

        AuthorDetails toSave = new AuthorDetails();

        toSave.setId(authorRequest.getId());
        toSave.setName(authorRequest.getName());
        toSave.setSurname(authorRequest.getSurname());
        toSave.setBookDetails(bookDetails);
        return authorDetailsRepository.save(toSave);


    }

    public List<AuthorDetails> getBooksByAuthors(Long id){
    Optional<BookDetails>bookDetails=booksDetailsService.getOneBookDetail(id);
List<AuthorDetails>list=authorDetailsRepository.findAll();

List<AuthorDetails>listForBookAuthor=new ArrayList<>();
for (AuthorDetails item:list
             ) {
  BookDetails bookDetails1=  item.getBookDetails();
if (bookDetails1.getId().equals(bookDetails.get().getId())){
    listForBookAuthor.add(item);
}
    }

if (listForBookAuthor.isEmpty()){
    listForBookAuthor.add(null);
}


       return listForBookAuthor;
    }


    public AuthorDetails updateOneAuthor(Long id, UpdateAuthorRequest newAuthor) {
        Optional<AuthorDetails> author = authorDetailsRepository.getAuthorById(id);


        if (author.isPresent()) {

            AuthorDetails foundAuthor = new AuthorDetails();


            foundAuthor.setName(newAuthor.getName());
            foundAuthor.setSurname(newAuthor.getSurname());


            return authorDetailsRepository.save(foundAuthor);
        }

        return null;
    }


    public void deleteOneAuthor(Long id) {

        Optional<AuthorDetails> foundUser = authorDetailsRepository.getAuthorById(id);


        foundUser.ifPresent(authorDetailsRepository::delete);


    }


}
