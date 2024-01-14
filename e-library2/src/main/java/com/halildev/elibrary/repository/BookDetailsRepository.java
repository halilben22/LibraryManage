package com.halildev.elibrary.repository;

import com.halildev.elibrary.models.BookDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookDetailsRepository extends JpaRepository<BookDetails, Long> {
    Optional<BookDetails> getOneBookDetailById(Long id);





    Optional<BookDetails> getBookDetailByBookName(String bookName);
}
