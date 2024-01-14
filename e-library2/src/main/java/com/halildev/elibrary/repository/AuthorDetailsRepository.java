package com.halildev.elibrary.repository;

import com.halildev.elibrary.models.AuthorDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AuthorDetailsRepository extends JpaRepository<AuthorDetails, Long> {
    Optional<AuthorDetails> getAuthorById(Long id);


}
