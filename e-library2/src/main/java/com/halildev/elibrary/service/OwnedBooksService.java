package com.halildev.elibrary.service;


import com.halildev.elibrary.dto.requests.UpdateOwnedBookRequest;
import com.halildev.elibrary.models.BookDetails;
import com.halildev.elibrary.models.OwnedBooks;
import com.halildev.elibrary.models.User;
import com.halildev.elibrary.repository.OwnedBooksRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OwnedBooksService {


    private final OwnedBooksRepository ownedBooksRepository;
    private final BooksDetailsService booksDetailsService;
    private final UserService userService;


    public OwnedBooksService(OwnedBooksRepository ownedBooksRepository, BooksDetailsService booksDetailsService, UserService userService) {
        this.ownedBooksRepository = ownedBooksRepository;
        this.booksDetailsService = booksDetailsService;
        this.userService = userService;
    }


    public List<OwnedBooks> getAllOwnedBookList() {


        return ownedBooksRepository.findAll();
    }


    public OwnedBooks getOneOwnedBook(Long id) {
        return ownedBooksRepository.getOwnedBookById(id);
    }

    public List<BookDetails> getOwnedBookByUserId(Long id){
        List<OwnedBooks> ownedBooks=ownedBooksRepository.findAll();
        List<BookDetails>books=new ArrayList<>();


        for (OwnedBooks item:ownedBooks
             ) {

            if (item.getUser().getId()==id){
                books.add(item.getBookDetails());
            }

        }

    return books;
    }

    public OwnedBooks createOwnedBook(OwnedBooks ownedBookRequest) {

        BookDetails bookDetails = booksDetailsService.getOneBookDetail(ownedBookRequest.getBookDetails().getId()).orElse(null);
        User user = userService.getOneUser(ownedBookRequest.getUser().getId()).orElse(null);

        assert bookDetails != null;
        if (bookDetails.getBookStock() != 0) {
            OwnedBooks toSave = new OwnedBooks();

            toSave.setBookDetails(bookDetails);
            toSave.setUser(user);

            bookDetails.setBookStock(bookDetails.getBookStock() - 1);


            return ownedBooksRepository.save(toSave);

        }

        return null;


    }


    public OwnedBooks updateOneOwnedBook(UpdateOwnedBookRequest ownedBookRequest, Long id) {
        BookDetails bookDetails = booksDetailsService.getOneBookDetail(ownedBookRequest.getBook_id()).orElse(null);
        User user = userService.getOneUser(ownedBookRequest.getUser_id()).orElse(null);

        assert bookDetails != null;
        if (bookDetails.getBookStock() != 0) {
            OwnedBooks ownedBook = ownedBooksRepository.getOwnedBookById(id);
            OwnedBooks toSave = new OwnedBooks();
            toSave.setBookDetails(bookDetails);
            toSave.setUser(user);

            bookDetails.setBookStock(bookDetails.getBookStock() - 1);


            return ownedBooksRepository.save(toSave);

        }


        return null;

    }


    public void deleteOneOwnedBook(Long id) {
        OwnedBooks ownedBook = ownedBooksRepository.getOwnedBookById(id);
        BookDetails bookDetails = booksDetailsService.getOneBookDetail(ownedBook.getBookDetails().getId()).orElse(null);

        assert bookDetails != null;
        bookDetails.setBookStock(bookDetails.getBookStock() + 1);
        ownedBooksRepository.delete(ownedBook);

    }

}
