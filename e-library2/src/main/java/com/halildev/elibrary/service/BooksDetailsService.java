package com.halildev.elibrary.service;


import com.halildev.elibrary.dto.requests.CreateBookRequest;
import com.halildev.elibrary.dto.requests.UpdateBookRequest;
import com.halildev.elibrary.models.AuthorDetails;
import com.halildev.elibrary.models.BookDetails;
import com.halildev.elibrary.repository.BookDetailsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BooksDetailsService {


    private final BookDetailsRepository bookDetailsRepository;


    public BooksDetailsService(BookDetailsRepository bookDetailsRepository) {
        this.bookDetailsRepository = bookDetailsRepository;

    }


    public List<BookDetails> getAllBooks() {


        return bookDetailsRepository.findAll();

    }


    public Optional<BookDetails> getOneBookDetail(Long book_id) {

        return bookDetailsRepository.getOneBookDetailById(book_id);
    }
    public Optional<BookDetails> getOneBookDetailByName(String book_name) {

        return bookDetailsRepository.getBookDetailByBookName(book_name);
    }


    public BookDetails createOneBookDetail(CreateBookRequest bookRequest) {


        BookDetails toSave = new BookDetails();


        toSave.setId(bookRequest.getId());

        toSave.setBookName(bookRequest.getBookName());

        toSave.setBookISBN(bookRequest.getBook_ISBN());
        toSave.setBookStock(bookRequest.getBookStock());

        toSave.setAuthorDetailsList(bookRequest.getAuthorDetailsList());


        return bookDetailsRepository.save(toSave);
    }


    public BookDetails updateOneBook(Long book_id, UpdateBookRequest bookRequest) {


        Optional<BookDetails> bookDetails = bookDetailsRepository.getOneBookDetailById(book_id);

        if (bookDetails.isPresent()) {

            BookDetails toSave = new BookDetails();
            toSave.setId(book_id);
            toSave.setBookName(bookRequest.getBookName());
            toSave.setBookISBN(bookRequest.getBook_ISBN());
            toSave.setBookStock(bookRequest.getBookStock());


            toSave.setAuthorDetailsList(bookRequest.getAuthorDetailsList());


            return bookDetailsRepository.save(toSave);


        }

        return null;
    }


    public void deleteOneBook(Long id) {
        Optional<BookDetails> bookDetails = bookDetailsRepository.getOneBookDetailById(id);


        bookDetails.ifPresent(bookDetailsRepository::delete);


    }
}
