package com.halildev.elibrary.repository;

import com.halildev.elibrary.models.OwnedBooks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnedBooksRepository extends JpaRepository<OwnedBooks,Long>
{
    OwnedBooks getOwnedBookById(Long id);

}
