package com.halildev.elibrary.dto.requests;


import com.halildev.elibrary.models.AuthorDetails;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CreateBookRequest {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String bookName;

    String book_ISBN;

    int bookStock;

    List<AuthorDetails> authorDetailsList;

}
